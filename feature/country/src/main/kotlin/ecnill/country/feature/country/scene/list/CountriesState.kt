package ecnill.country.feature.country.scene.list

import ecnill.country.model.CountryShortModel

internal data class CountriesState(
    val loading: Boolean = false,
    val swiping: Boolean = false,
    val region: String = "",
    val countries: List<CountryShortModel> = emptyList()
)