package ecnill.country.feature.country.scene.detail

import ecnill.country.model.CountryRichModel

internal sealed interface CountryAction {

    data class Fetching(val country: String) : CountryAction

    data class Fetched(val country: CountryRichModel?) : CountryAction
}