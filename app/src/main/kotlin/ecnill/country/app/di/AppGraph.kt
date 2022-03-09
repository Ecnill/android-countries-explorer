package ecnill.country.app.di

import ecnill.country.feature.country.di.CountryGraph
import ecnill.country.feature.region.di.RegionGraph

internal object AppGraph {

    private val features = RegionGraph.module + CountryGraph.module

    val modules = features
}