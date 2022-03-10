package ecnill.country.model

/**
 * The pure model represents information about the country in a short format.
 */
data class CountryShortModel(
    val officialName: String,
    val commonName: String,
    val flagPng: String?,
    val capital: String,
)