package ecnill.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class DesignColors(
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    background: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onError: Color,
    divider: Color,
    primaryText: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        internal set
    var primaryVariant by mutableStateOf(primaryVariant)
        internal set
    var secondary by mutableStateOf(secondary)
        internal set
    var background by mutableStateOf(background)
        internal set
    var error by mutableStateOf(error)
        internal set
    var onPrimary by mutableStateOf(onPrimary)
        internal set
    var onSecondary by mutableStateOf(onSecondary)
        internal set
    var onBackground by mutableStateOf(onBackground)
        internal set
    var onError by mutableStateOf(onError)
        internal set
    var divider by mutableStateOf(divider)
        internal set
    var primaryText by mutableStateOf(primaryText)
        internal set
    var isLight by mutableStateOf(isLight)
        internal set

    override fun toString(): String {
        return "Colors(" +
            "primary=$primary, " +
            "primaryVariant=$primaryVariant, " +
            "secondary=$secondary, " +
            "background=$background, " +
            "error=$error, " +
            "onPrimary=$onPrimary, " +
            "onSecondary=$onSecondary, " +
            "onBackground=$onBackground, " +
            "onError=$onError, " +
            "divider=$divider, " +
            "primaryText=$primaryText, " +
            "isLight=$isLight" +
            ")"
    }
}

/**
 * Creates a complete color definition for the
 * @see darkColors
 */
internal fun lightColors(
    primary: Color = Color(0xFF009688),
    primaryVariant: Color = Color(0xFF00796B),
    secondary: Color = Color(0xFF607D8B),
    background: Color = Color.White,
    error: Color = Color(0xFFFF5252),
    onPrimary: Color = Color.White,
    onSecondary: Color = Color.White,
    onBackground: Color = Color.Black,
    onError: Color = Color.White,
    divider: Color = Color(0xFFBDBDBD),
    primaryText: Color = Color(0xFF212121),
): DesignColors = DesignColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onError = onError,
    divider = divider,
    primaryText = primaryText,
    isLight = true
)

/**
 * Creates a complete color definition for the
 * [Material color specification](https://material.io/design/color/the-color-system.html#color-theme-creation)
 * using the default dark theme values.
 *
 * @see lightColors
 */
internal fun darkColors(
    // TODO: these colors are temporary, define a better dark palette
    primary: Color = Color(0xFFBB86FC),
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Color(0xFF03DAC6),
    background: Color = Color(0xFF121212),
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Color.White,
    onSecondary: Color = Color.White,
    onBackground: Color = Color.White,
    onError: Color = Color.White,
    divider: Color = Color.White,
    primaryText: Color = Color.White,
): DesignColors = DesignColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onError = onError,
    divider = divider,
    primaryText = primaryText,
    isLight = false
)

/**
 * Updates the internal values of the given [DesignColors] with values from the [other] [DesignColors]. This
 * allows efficiently updating a subset of [DesignColors], without recomposing every composable that
 * consumes values from [LocalDesignColors].
 */
internal fun DesignColors.updateColorsFrom(other: DesignColors) {
    primary = other.primary
    primaryVariant = other.primaryVariant
    secondary = other.secondary
    background = other.background
    error = other.error
    onPrimary = other.onPrimary
    onSecondary = other.onSecondary
    onBackground = other.onBackground
    onError = other.onError
    divider = other.divider
    primaryText = other.primaryText
    isLight = other.isLight
}

/**
 * CompositionLocal used to pass [DesignColors] down the tree.
 *
 * Setting the value here is typically done as part of [MaterialTheme], which will
 * automatically handle efficiently updating any changed colors without causing unnecessary
 * recompositions, using [DesignColors.updateColorsFrom].
 * To retrieve the current value of this CompositionLocal, use [MaterialTheme.colors].
 */
internal val LocalDesignColors = staticCompositionLocalOf { lightColors() }