package ecnill.country.feature.region.scene

import androidx.lifecycle.ViewModel
import ecnill.country.feature.region.data.RegionRepository

internal class RegionsViewModel(repository: RegionRepository) : ViewModel() {

    val regions = repository.getRegions()
}