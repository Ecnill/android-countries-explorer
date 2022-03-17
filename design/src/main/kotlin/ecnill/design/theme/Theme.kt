package ecnill.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CountryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors() else lightColors()

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(Color.Transparent)
    } else {
        systemUiController.setSystemBarsColor(CountryTheme.colors.primaryVariant)
    }

    CompositionLocalProvider(
        LocalDesignColors provides colors,
    ) {
        MaterialTheme(
            colors = Colors(
                primary = colors.primary,
                primaryVariant = colors.primaryVariant,
                secondary = colors.secondary,
                secondaryVariant = colors.secondaryVariant,
                background = colors.background,
                surface = colors.surface,
                error = colors.error,
                onPrimary = colors.onPrimary,
                onSecondary = colors.onSecondary,
                onBackground = colors.onBackground,
                onSurface = colors.onSurface,
                onError = colors.onError,
                isLight = darkTheme
            ),
            content = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    content()
                }
            }
        )
    }
}

/**
 * The access to the colors and other components of the theme.
 */
object CountryTheme {

    /**
     * Retrieves the current [DesignColors] at the call site's position in the hierarchy.
     */
    val colors: DesignColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDesignColors.current
}