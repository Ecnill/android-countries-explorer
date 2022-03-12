package ecnill.country.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ecnill.country.feature.country.scene.detail.CountryScreen
import ecnill.country.feature.country.scene.list.CountriesScreen
import ecnill.country.feature.launch.scene.LaunchScreen
import ecnill.country.feature.region.scene.RegionsScreen

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "launch") {

        composable("launch") {
            LaunchScreen {
                navController.popBackStack()
                navController.navigate("regions")
            }
        }

        composable("regions") {
            RegionsScreen { region, resId -> navController.navigate("countries/$region,$resId") }
        }

        countryGraph(navController)
    }
}

private fun NavGraphBuilder.countryGraph(navController: NavController) {
    navigation(startDestination = "countries/{region},{resId}", route = "countries") {
        composable(
            route = "countries/{region},{resId}",
            arguments = listOf(
                navArgument("region") { type = NavType.StringType },
                navArgument("resId") { type = NavType.IntType }
            ),
        ) { backStackEntry ->
            CountriesScreen(
                region = backStackEntry.arguments?.getString("region").orEmpty(),
                regionResId = backStackEntry.arguments?.getInt("resId"),
                navigateToNext = { country -> navController.navigate("countries/region/$country") },
                navigateBack = navController::navigateUp
            )
        }
        composable(
            route = "countries/region/{country}",
            arguments = listOf(navArgument("country") { type = NavType.StringType }),
        ) { backStackEntry ->
            CountryScreen(
                country = backStackEntry.arguments?.getString("country").orEmpty(),
                navigateBack = navController::navigateUp
            )
        }
    }
}