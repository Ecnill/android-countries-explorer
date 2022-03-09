package ecnill.design.component

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ecnill.design.R
import ecnill.design.theme.DesignPreviewTheme
import ecnill.design.theme.DesignTheme

/**
 * A holder for navigation buttons in a toolbar.
 */
sealed interface Header {

    /**
     * Describes the navigation button.
     */
    data class NavigationButton(
        val action: () -> Unit,
        val type: Type = Type.Back,
    ) {
        /**
         * Navigation button types with resource and content description IDs. Currently Back and Close are supported.
         */
        enum class Type(internal val icon: Int, internal val contentDescriptionId: Int) {
            Back(R.drawable.ic_arrow, R.string.design_button_back),
            Close(R.drawable.ic_close, R.string.design_button_close)
        }
    }
}

/**
 * A toolbar with a title and navigation button.
 *
 * @param navigationButton  the navigation back or close icon wrapper for an icon with an action.
 * @param title             the toolbar title.
 *
 *  _________________________________________________
 * |  <--                Title                      |
 * --------------------------------------------------
 */
@Composable
fun Header(
    modifier: Modifier = Modifier,
    navigationButton: Header.NavigationButton? = null,
    title: String? = null,
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        title = {
            Text(
                text = title.orEmpty(),
                color = DesignTheme.colors.onPrimary,
            )
        },
        backgroundColor = DesignTheme.colors.primaryVariant,
        navigationIcon = {
            if (navigationButton != null) {
                IconButton(onClick = navigationButton.action) {
                    Icon(
                        tint = DesignTheme.colors.onPrimary,
                        painter = painterResource(id = navigationButton.type.icon),
                        contentDescription = stringResource(id = navigationButton.type.contentDescriptionId)
                    )
                }
            }
        }
    )
}

@Preview(name = "Day Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeaderPreview() = DesignPreviewTheme {
    Header(
        title = "Header Preview",
        navigationButton = Header.NavigationButton(action = {}, type = Header.NavigationButton.Type.Back)
    )
}
