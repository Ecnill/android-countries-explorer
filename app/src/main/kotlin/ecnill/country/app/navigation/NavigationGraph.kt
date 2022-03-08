package ecnill.country.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ecnill.country.feature.launch.scene.LaunchScreen

@Composable
internal fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "launch") {

        composable("launch") {
            LaunchScreen {
                navController.popBackStack()
            }
        }
    }
}