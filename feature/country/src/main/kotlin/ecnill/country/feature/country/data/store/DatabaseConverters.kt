package ecnill.country.feature.country.data.store

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ecnill.country.feature.country.model.CountryDetailItem
import ecnill.country.feature.country.model.CountryListItem
import ecnill.country.network.model.CountryLongResponse
import ecnill.country.network.model.CountryShortResponse

internal object DatabaseConverters {

    @ProvidedTypeConverter
    class CountryList {

        @TypeConverter
        fun fromListItem(value: CountryListItem) = CountryShortResponse(
            region = value.region,
            officialName = value.officialName,
            commonName = value.commonName,
            flagPng = value.flagPng,
            capital = value.capital
        )

        @TypeConverter
        fun toListItem(value: CountryShortResponse) = CountryListItem(
            region = value.region,
            officialName = value.officialName,
            commonName = value.commonName,
            flagPng = value.flagPng,
            capital = value.capital
        )
    }

    @ProvidedTypeConverter
    class CountryDetail {
        @TypeConverter
        fun fromDetailItem(value: CountryDetailItem) = CountryLongResponse(
            officialName = value.officialName,
            commonName = value.commonName,
            flagPng = value.flagPng,
            capital = value.capital,
            currencies = value.currencies,
            languages = value.languages,
            area = value.area,
            population = value.population,
            mapUrl = value.mapUrl,
        )

        @TypeConverter
        fun toDetailItem(value: CountryLongResponse) = CountryDetailItem(
            officialName = value.officialName,
            commonName = value.commonName,
            flagPng = value.flagPng,
            capital = value.capital,
            currencies = value.currencies,
            languages = value.languages,
            area = value.area,
            population = value.population,
            mapUrl = value.mapUrl,
        )
    }

    internal class ListString {

        @TypeConverter
        fun stringToList(value: String?): List<String> = value?.split(", ")?.toList().orEmpty()

        @TypeConverter
        fun listToString(values: List<String>): String = values.joinToString()
    }
}