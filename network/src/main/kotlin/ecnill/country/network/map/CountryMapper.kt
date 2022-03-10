package ecnill.country.network.map

import ecnill.country.model.CountryRichModel
import ecnill.country.model.CountryShortModel
import ecnill.country.network.api.CountryResponse

internal object CountryMapper {

    fun CountryResponse.toShort() = CountryShortModel(
        officialName = name.official,
        commonName = name.common,
        flagPng = flags.png,
        capital = capital?.firstOrNull().orEmpty(),
    )

    fun CountryResponse.toRich() = CountryRichModel(
        officialName = name.official,
        commonName = name.common,
        flagPng = flags.png,
        capital = capital?.firstOrNull().orEmpty(),
        population = population,
        currencies = currencies.items,
        mapUrl = maps.googleMaps,
        languages = languages.items,
        area = area,
    )
}