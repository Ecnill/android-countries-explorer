package ecnill.country.feature.country.di

import ecnill.country.feature.country.data.CountryRepository
import ecnill.country.feature.country.scene.list.CountriesReducer
import ecnill.country.feature.country.scene.list.CountriesViewModel
import ecnill.country.feature.country.scene.list.FetchCountriesEffect.Factory
import ecnill.country.network.di.NetworkingGraph
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * The country feature dependencies.
 */
object CountryGraph {

    private val feature = module {
        singleOf(::CountryRepository)

        factoryOf(::Factory)
        factoryOf(::CountriesReducer)
        viewModelOf(::CountriesViewModel)
    }

    val module: List<Module> = NetworkingGraph.module + feature
}