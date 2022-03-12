package ecnill.design.component

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import ecnill.design.R
import ecnill.design.theme.DesignTheme

@Composable
fun RemoteImage(
    imageUrl: String,
    modifier: Modifier,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        modifier = modifier,
        contentDescription = null,
        loading = { ProgressIndicator() },
        error = {
            Icon(
                modifier = modifier,
                tint = DesignTheme.colors.error,
                painter = painterResource(id = R.drawable.ic_flag),
                contentDescription = stringResource(id = R.string.design_error_icon_content_description)
            )
        },
        success = {
            Image(
                modifier = modifier,
                painter = it.painter,
                contentDescription = null,
            )
        }
    )
}