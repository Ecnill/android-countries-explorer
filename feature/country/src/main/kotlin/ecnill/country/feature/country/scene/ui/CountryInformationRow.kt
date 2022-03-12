package ecnill.country.feature.country.scene.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ecnill.design.theme.DesignTheme

@Composable
internal fun CountryInformationRow(title: String, value: String, modifier: Modifier = Modifier) {
    if (value.isNotEmpty()) {
        Row(modifier = modifier) {
            Text(text = title, color = DesignTheme.colors.primaryText)
            Text(text = ": $value", fontWeight = FontWeight.SemiBold, color = DesignTheme.colors.primaryText)
        }
    }
}