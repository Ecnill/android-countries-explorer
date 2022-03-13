package ecnill.country.network.api

import retrofit2.http.GET
import retrofit2.http.Path

internal interface CountryApi {

    @GET("region/{region}")
    suspend fun getCountries(@Path("region") region: String): List<CountryJson>

    @GET("name/{name}")
    suspend fun getCountry(@Path("name") country: String): List<CountryJson>
}