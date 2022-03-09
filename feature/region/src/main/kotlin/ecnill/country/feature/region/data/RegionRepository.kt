package ecnill.country.feature.region.data

internal class RegionRepository {

    fun getRegions(): Array<Region> = Region.values()
}