package ecnill.country.feature.country.scene.list

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ecnill.design.theme.DesignTheme

@Suppress("UnusedPrivateMember")
@Composable
fun CountriesScreen(
    region: String,
    @StringRes regionResId: Int?,
    navigateToNext: (country: String) -> Unit,
    navigateBack: () -> Unit,
) = DesignTheme {
    Text(text = region, modifier = Modifier.padding(24.dp).clickable { navigateToNext("CountryName") })
}