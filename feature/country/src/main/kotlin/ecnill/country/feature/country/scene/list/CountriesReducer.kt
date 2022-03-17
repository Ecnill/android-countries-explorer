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
                copy(loading = false, swiping = false, error = action.countries.isEmpty(), countries = action.countries)
            }
            is CountriesAction.Fetching -> {
                state.mutate { copy(loading = true, error = false, region = action.region) }
                fetchingEffect(action.region, false)
            }
            CountriesAction.Refreshing -> {
                state.mutate { copy(swiping = true) }
                fetchingEffect(state.get().region, true)
            }
        }
    }

    private fun fetchingEffect(region: String, forceFetch: Boolean) =
        effectOf(fetchCountries.create(RegionRequest(region), forceFetch))
}

internal class FetchCountriesEffect(
    private val region: RegionRequest,
    private val forceFetch: Boolean,
    private val repository: CountryRepository,
) : Effect<CountriesAction.Fetched> {

    override suspend fun invoke() = CountriesAction.Fetched(repository.fetchList(region, forceFetch))

    class Factory(private val repository: CountryRepository) {

        fun create(region: RegionRequest, forceFetch: Boolean) =
            FetchCountriesEffect(region = region, forceFetch = forceFetch, repository)
    }
}