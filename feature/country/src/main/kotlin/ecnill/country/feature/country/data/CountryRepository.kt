package ecnill.country.feature.country.data

import ecnill.country.feature.country.data.store.CountryDao
import ecnill.country.feature.country.model.CountryDetailItem
import ecnill.country.feature.country.model.CountryListItem
import ecnill.country.feature.country.model.CountryRequest
import ecnill.country.feature.country.model.RegionRequest
import ecnill.country.network.CountryNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CountryRepository(
    private val service: CountryNetworkService,
    private val countryDao: CountryDao,
) {

    suspend fun fetchDetail(country: CountryRequest): CountryDetailItem? = withContext(Dispatchers.IO) {
        val detailFromStore = countryDao.getDetail(country.value)
        return@withContext detailFromStore ?: service.fetchDetail(country.value)?.let { detailFromServer ->
            CountryDetailItem(
                officialName = detailFromServer.officialName,
                commonName = detailFromServer.commonName,
                flagPng = detailFromServer.flagPng,
                capital = detailFromServer.capital,
                population = detailFromServer.population,
                currencies = detailFromServer.currencies,
                mapUrl = detailFromServer.mapUrl,
                languages = detailFromServer.languages,
                area = detailFromServer.area
            )
        }.also { it?.let { countryDao.insertDetail(it) } }
    }

    suspend fun fetchList(
        region: RegionRequest,
        forceFetch: Boolean
    ): List<CountryListItem> = withContext(Dispatchers.IO) {
        when {
            forceFetch || countryDao.getCountries(region.value).isEmpty() -> {
                service.fetchList(region.value).map { value ->
                    CountryListItem(
                        region = region.value,
                        officialName = value.officialName,
                        commonName = value.commonName,
                        flagPng = value.flagPng,
                        capital = value.capital
                    )
                }.also { countryDao.insertCountries(it) }
            }
            else -> countryDao.getCountries(region.value)
        }
    }
}