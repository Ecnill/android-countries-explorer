package ecnill.country.feature.country.di

import androidx.room.Room
import ecnill.country.feature.country.data.CountryRepository
import ecnill.country.feature.country.data.store.AppDatabase
import ecnill.country.feature.country.format.CountryDetailFormat
import ecnill.country.feature.country.scene.detail.CountryReducer
import ecnill.country.feature.country.scene.detail.CountryViewModel
import ecnill.country.feature.country.scene.detail.FetchCountryEffect
import ecnill.country.feature.country.scene.list.CountriesReducer
import ecnill.country.feature.country.scene.list.CountriesViewModel
import ecnill.country.feature.country.scene.list.FetchCountriesEffect
import ecnill.country.network.di.NetworkingGraph
import java.util.Locale
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * The country feature dependencies.
 */
object CountryGraph {

    private const val appDatabaseName = "country-app-database"

    private val feature = module {
        single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, appDatabaseName).build() }
        single { get<AppDatabase>().countryDao() }
        singleOf(::CountryRepository)

        factory { FetchCountriesEffect.Factory(get()) }
        factoryOf(::CountriesReducer)
        viewModelOf(::CountriesViewModel)

        factory { CountryDetailFormat(Locale.US) }
        factory { FetchCountryEffect.Factory(get()) }
        factoryOf(::CountryReducer)
        viewModelOf(::CountryViewModel)
    }

    val module: List<Module> = NetworkingGraph.module + feature
}