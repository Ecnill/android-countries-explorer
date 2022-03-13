package ecnill.country.network

import ecnill.country.network.api.CountryApi
import ecnill.country.network.map.CountryMapper.toRich
import ecnill.country.network.map.CountryMapper.toShort
import ecnill.country.network.model.CountryLongResponse
import ecnill.country.network.model.CountryShortResponse
import retrofit2.Retrofit
import timber.log.Timber

/**
 * The entry point to networking.
 */
class CountryNetworkService(retrofit: Retrofit) {

    private val api = retrofit.create(CountryApi::class.java)

    suspend fun fetchList(region: String): List<CountryShortResponse> = runCatching { api.getCountries(region) }.fold(
        onSuccess = { response -> response.map { it.toShort() } },
        onFailure = {
            Timber.e(it.localizedMessage.orEmpty())
            emptyList()
        }
    )

    suspend fun fetchDetail(country: String): CountryLongResponse? = runCatching { api.getCountry(country) }.fold(
        onSuccess = { it.firstOrNull()?.toRich() },
        onFailure = {
            Timber.e(it.localizedMessage.orEmpty())
            null
        }
    )
}