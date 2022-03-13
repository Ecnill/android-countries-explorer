package ecnill.country.feature.country.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ecnill.country.feature.country.data.store.DatabaseConverters

@Entity(tableName = "countryDetail")
internal data class CountryDetailItem(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val officialName: String,
    val commonName: String,
    val flagPng: String?,
    val capital: String,
    val population: Long,
    @TypeConverters(DatabaseConverters.ListString::class) val currencies: List<String>,
    val mapUrl: String?,
    @TypeConverters(DatabaseConverters.ListString::class) val languages: List<String>,
    val area: Double
)
