package ecnill.country.network.model

/**
 * The network response about the country in a short format.
 */
data class CountryShortResponse(
    val region: String,
    val officialName: String,
    val commonName: String,
    val flagPng: String?,
    val capital: String,
)