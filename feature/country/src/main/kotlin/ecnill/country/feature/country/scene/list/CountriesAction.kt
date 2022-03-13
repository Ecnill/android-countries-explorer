package ecnill.country.feature.country.scene.list

import ecnill.country.feature.country.model.CountryListItem

internal sealed interface CountriesAction {

    data class Fetching(val region: String) : CountriesAction

    data class Fetched(val countries: List<CountryListItem>) : CountriesAction

    object Refreshing : CountriesAction
}