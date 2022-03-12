package ecnill.country.feature.country.format

import java.text.NumberFormat
import java.util.Locale

internal class CountryDetailFormat(private val locale: Locale) {

    fun area(area: Double): String = NumberFormat.getNumberInstance(locale).format(area) + " m2"

    fun population(population: Long): String = NumberFormat.getNumberInstance(locale).format(population)

    fun languages(languages: List<String>): String = languages.joinToString()

    fun currencies(currencies: List<String>): String = currencies.joinToString()
}