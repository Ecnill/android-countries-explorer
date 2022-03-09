package ecnill.country.feature.region.di

import ecnill.country.feature.region.data.RegionRepository
import ecnill.country.feature.region.scene.RegionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * The region feature dependencies.
 */
object RegionGraph {

    val module = module {
        singleOf(::RegionRepository)
        viewModelOf(::RegionsViewModel)
    }
}