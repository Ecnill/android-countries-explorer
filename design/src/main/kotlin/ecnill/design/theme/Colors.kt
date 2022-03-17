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
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
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
    var secondaryVariant by mutableStateOf(secondaryVariant)
        internal set
    var background by mutableStateOf(background)
        internal set
    var surface by mutableStateOf(surface)
        internal set
    var error by mutableStateOf(error)
        internal set
    var onPrimary by mutableStateOf(onPrimary)
        internal set
    var onSecondary by mutableStateOf(onSecondary)
        internal set
    var onBackground by mutableStateOf(onBackground)
        internal set
    var onSurface by mutableStateOf(onSurface)
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
            "secondaryVariant=$secondaryVariant, " +
            "background=$background, " +
            "surface=$surface, " +
            "error=$error, " +
            "onPrimary=$onPrimary, " +
            "onSecondary=$onSecondary, " +
            "onBackground=$onBackground, " +
            "onSurface=$onSurface, " +
            "onError=$onError, " +
            "divider=$divider" +
            "primaryText=$primaryText" +
            "isLight=$isLight" +
            ")"
    }
}

/**
 * Creates a complete color definition for the
 * @see darkColors
 */
internal fun lightColors(
    primary: Color = Color(0xFF26a69a),
    primaryVariant: Color = Color(0xFF00766c),
    secondary: Color = Color(0xFF4fc3f7),
    secondaryVariant: Color = Color(0xFF0093c4),
    background: Color = Color(0xFFEBF7FC),
    surface: Color = Color.White,
    error: Color = Color(0xFFFF5252),
    onPrimary: Color = Color(0xFFEEEDED),
    onSecondary: Color = Color.White,
    onBackground: Color = Color.Black,
    onSurface: Color = Color.Black,
    onError: Color = Color.White,
    divider: Color = Color(0xFFBDBDBD),
    primaryText: Color = Color(0xFF212121),
): DesignColors = DesignColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
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
    primary: Color = Color(0xFF004d40),
    primaryVariant: Color = Color(0xFF00251a),
    secondary: Color = Color(0xFF484848),
    secondaryVariant: Color = Color(0xFF111111),
    background: Color = Color(0xFF212121),
    surface: Color = Color(0xFF121212),
    error: Color = Color(0xFFBE3E55),
    onPrimary: Color = Color(0xB3FFFFFF),
    onSecondary: Color = Color(0xA6FFFFFF),
    onBackground: Color = Color(0xA6FFFFFF),
    onSurface: Color = Color(0xA6FFFFFF),
    onError: Color = Color.White,
    divider: Color = Color(0x80FFFFFF),
    primaryText: Color = Color(0xB3FFFFFF),
): DesignColors = DesignColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
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