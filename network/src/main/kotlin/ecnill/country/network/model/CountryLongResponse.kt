package ecnill.country.network.model

/**
 * The network response about the country in a long format.
 */
data class CountryLongResponse(
    val officialName: String,
    val commonName: String,
    val flagPng: String?,
    val capital: String,
    val population: Long,
    val currencies: List<String>,
    val mapUrl: String?,
    val languages: List<String>,
    val area: Double
)