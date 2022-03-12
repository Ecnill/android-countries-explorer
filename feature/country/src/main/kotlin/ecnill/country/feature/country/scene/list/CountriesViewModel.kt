package ecnill.country.feature.country.scene.list

import ecnill.arch.scene.ReducibleViewModel

internal class CountriesViewModel(
    region: String,
    reducer: CountriesReducer,
) : ReducibleViewModel.Implementation<CountriesState, CountriesAction>(
    CountriesState(), reducer, listOf(CountriesAction.Fetching(region))
)