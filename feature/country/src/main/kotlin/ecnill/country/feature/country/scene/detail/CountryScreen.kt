package ecnill.country.feature.country.scene.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ecnill.design.theme.DesignTheme

@Composable
fun CountryScreen(country: String) = DesignTheme {
    Text(text = country)
}