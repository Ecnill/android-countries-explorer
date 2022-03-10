package ecnill.country.network.api

import com.squareup.moshi.FromJson

internal object CustomAdapter {

    class Language {

        @FromJson
        fun fromJson(languages: Map<String, String>): Languages =
            Languages(languages.values.toList())
    }

    class Currency {

        @FromJson
        fun fromJson(currencies: Map<String, Map<String, String>>) = Currencies(
            currencies.values.map { it.toList().joinToString(" ") { it.second } }
        )
    }
}