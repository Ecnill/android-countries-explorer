package ecnill.country.feature.country.scene.list

import ecnill.country.feature.country.model.CountryListItem

internal data class CountriesState(
    val loading: Boolean = false,
    val swiping: Boolean = false,
    val error: Boolean = false,
    val region: String = "",
    val countries: List<CountryListItem> = emptyList()
)