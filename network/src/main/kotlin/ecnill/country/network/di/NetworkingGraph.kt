package ecnill.country.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ecnill.country.network.CountryNetworkService
import ecnill.country.network.api.ApiConfig
import ecnill.country.network.api.CustomAdapter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * The network module dependencies.
 */
object NetworkingGraph {

    val module = module {
        single {
            Moshi.Builder()
                .add(CustomAdapter.Currency())
                .add(CustomAdapter.Language())
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(ApiConfig.baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()
        }

        singleOf(::CountryNetworkService)
    }
}