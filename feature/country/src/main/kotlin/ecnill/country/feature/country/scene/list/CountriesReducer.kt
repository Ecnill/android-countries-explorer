package ecnill.country.feature.country.scene.list

import ecnill.arch.scene.Effect
import ecnill.arch.scene.Mutable
import ecnill.arch.scene.Reducer
import ecnill.arch.scene.effectOf
import ecnill.arch.scene.withNoEffect
import ecnill.country.feature.country.data.CountryRepository
import ecnill.country.feature.country.model.RegionRequest

internal class CountriesReducer(
    private val fetchCountries: FetchCountriesEffect.Factory,
) : Reducer<CountriesState, CountriesAction> {

    override fun reduce(state: Mutable<CountriesState>, action: CountriesAction): List<Effect<CountriesAction>> {
        return when (action) {
            is CountriesAction.Fetched -> state.withNoEffect {
                copy(loading = false, swiping = false, countries = action.countries)
            }
            is CountriesAction.Fetching -> {
                state.mutate { copy(loading = true, region = action.region) }
                fetchingEffect(action.region)
            }
            CountriesAction.Refreshing -> {
                state.mutate { copy(swiping = true) }
                fetchingEffect(state.get().region)
            }
        }
    }

    private fun fetchingEffect(region: String) = effectOf(fetchCountries.create(RegionRequest(region)))
}

internal class FetchCountriesEffect(
    private val region: RegionRequest,
    private val countryRepository: CountryRepository,
) : Effect<CountriesAction.Fetched> {

    override suspend fun invoke() = CountriesAction.Fetched(countryRepository.fetchList(region))

    class Factory(private val countryRepository: CountryRepository) {

        fun create(region: RegionRequest) = FetchCountriesEffect(region, countryRepository)
    }
}