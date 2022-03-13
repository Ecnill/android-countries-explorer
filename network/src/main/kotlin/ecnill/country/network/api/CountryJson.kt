package ecnill.country.network.api

import com.squareup.moshi.JsonClass

/**
 * This is generated JSON -> Kotlin POJO entity, naming and availability of properties doesn't matter.
 */
@JsonClass(generateAdapter = true)
internal data class CountryJson(
    val name: Name,
    val tld: List<String>?,
    val independent: Boolean?,
    val status: String,
    val unMember: Boolean,
    val currencies: Currencies,
    val capital: List<String>?,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val languages: Languages,
    val translations: Map<String, Translation>,
    val latlng: List<Double>,
    val landlocked: Boolean,
    val borders: List<String>?,
    val area: Double,
    val flag: String?,
    val maps: Maps,
    val population: Long,
    val fifa: String?,
    val car: Car,
    val timezones: List<String>,
    val continents: List<String>,
    val flags: CoatOfArms,
    val coatOfArms: CoatOfArms,
    val startOfWeek: String,
    val capitalInfo: CapitalInfo,
    val postalCode: PostalCode?
)

internal data class CapitalInfo(
    val latlng: List<Double>?
)

internal data class Car(
    val signs: List<String>?,
    val side: String
)

internal data class CoatOfArms(
    val png: String?,
    val svg: String?
)

internal data class Currencies(
    val items: List<String>
)

internal data class Languages(
    val items: List<String>
)

internal data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)

internal data class Name(
    val common: String,
    val official: String,
    val nativeName: NativeName
)

internal data class NativeName(
    val ron: Translation?
)

internal data class Translation(
    val official: String,
    val common: String
)

internal data class PostalCode(
    val format: String,
    val regex: String?
)