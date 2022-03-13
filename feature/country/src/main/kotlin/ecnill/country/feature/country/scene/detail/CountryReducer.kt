package ecnill.country.feature.country.scene.detail

import ecnill.arch.scene.Effect
import ecnill.arch.scene.Mutable
import ecnill.arch.scene.Reducer
import ecnill.arch.scene.effectOf
import ecnill.arch.scene.withNoEffect
import ecnill.country.feature.country.R
import ecnill.country.feature.country.data.CountryRepository
import ecnill.country.feature.country.format.CountryDetailFormat
import ecnill.country.feature.country.model.CountryDetailItem
import ecnill.country.feature.country.model.CountryRequest
import ecnill.country.feature.country.scene.detail.CountryState.Property

internal class CountryReducer(
    private val fetchCountry: FetchCountryEffect.Factory,
    private val format: CountryDetailFormat,
) : Reducer<CountryState, CountryAction> {

    override fun reduce(state: Mutable<CountryState>, action: CountryAction): List<Effect<CountryAction>> {
        return when (action) {
            is CountryAction.Fetched -> state.withNoEffect {
                copy(
                    loading = false,
                    error = action.country == null,
                    officialName = action.country?.officialName.orEmpty(),
                    flagPng = action.country?.flagPng,
                    properties = action.country.toProperties(),
                    mapUrl = action.country?.mapUrl,
                )
            }
            is CountryAction.Fetching -> {
                state.mutate { copy(loading = true, error = false, officialName = action.country) }
                effectOf(fetchCountry.create(CountryRequest(action.country)))
            }
        }
    }

    private fun CountryDetailItem?.toProperties(): List<Property> = if (this == null) {
        emptyList()
    } else {
        buildList {
            add(Property(titleResId = R.string.country_detail_info_unofficial_name, value = commonName))
            add(Property(titleResId = R.string.country_detail_info_capital, value = capital))
            add(Property(titleResId = R.string.country_detail_info_area, value = format.area(area)))
            add(Property(titleResId = R.string.country_detail_info_population, value = format.population(population)))
            add(Property(titleResId = R.string.country_detail_info_currencies, value = format.currencies(currencies)))
            add(Property(titleResId = R.string.country_detail_info_languages, value = format.languages(languages)))
        }
    }
}

internal class FetchCountryEffect(
    private val country: CountryRequest,
    private val countryRepository: CountryRepository,
) : Effect<CountryAction.Fetched> {

    override suspend fun invoke() = CountryAction.Fetched(countryRepository.fetchDetail(country))

    class Factory(private val countryRepository: CountryRepository) {

        fun create(country: CountryRequest) = FetchCountryEffect(country, countryRepository)
    }
}