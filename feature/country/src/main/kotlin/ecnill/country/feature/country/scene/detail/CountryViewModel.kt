package ecnill.country.feature.country.scene.detail

import ecnill.arch.scene.ReducibleViewModel

internal class CountryViewModel(
    country: String,
    reducer: CountryReducer,
) : ReducibleViewModel.Implementation<CountryState, CountryAction>(
    CountryState(), reducer, listOf(CountryAction.Fetching(country))
)