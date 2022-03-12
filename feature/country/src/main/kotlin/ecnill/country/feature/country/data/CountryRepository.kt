package ecnill.country.feature.country.data

import ecnill.country.feature.country.model.RegionRequest
import ecnill.country.model.CountryShortModel
import ecnill.country.network.CountryNetworkService

internal class CountryRepository(private val service: CountryNetworkService) {

    suspend fun fetchList(region: RegionRequest): List<CountryShortModel> = service.fetchList(region.value)
}