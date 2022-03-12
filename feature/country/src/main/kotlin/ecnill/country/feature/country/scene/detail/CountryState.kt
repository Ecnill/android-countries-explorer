package ecnill.country.feature.country.scene.detail

import androidx.annotation.StringRes

internal data class CountryState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val officialName: String = "",
    val flagPng: String? = null,
    val properties: List<Property> = emptyList(),
    val mapUrl: String? = null,
) {

    data class Property(
        @StringRes val titleResId: Int,
        val value: String
    )
}