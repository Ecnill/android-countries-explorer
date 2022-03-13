package ecnill.country.network.map

import ecnill.country.network.api.CountryJson
import ecnill.country.network.model.CountryLongResponse
import ecnill.country.network.model.CountryShortResponse

internal object CountryMapper {

    fun CountryJson.toShort() = CountryShortResponse(
        region = region,
        officialName = name.official,
        commonName = name.common,
        flagPng = flags.png,
        capital = capital?.firstOrNull().orEmpty(),
    )

    fun CountryJson.toRich() = CountryLongResponse(
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