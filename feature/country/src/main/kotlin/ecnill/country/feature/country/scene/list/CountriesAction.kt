package ecnill.country.feature.country.scene.list

import ecnill.country.model.CountryShortModel

internal sealed interface CountriesAction {

    data class Fetching(val region: String) : CountriesAction

    data class Fetched(val countries: List<CountryShortModel>) : CountriesAction

    object Refreshing : CountriesAction
}