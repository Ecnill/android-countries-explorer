package ecnill.country.feature.country.scene.detail

import ecnill.country.feature.country.model.CountryDetailItem

internal sealed interface CountryAction {

    data class Fetching(val country: String) : CountryAction

    data class Fetched(val country: CountryDetailItem?) : CountryAction
}