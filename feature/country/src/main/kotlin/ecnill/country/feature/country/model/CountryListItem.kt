package ecnill.country.feature.country.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
internal data class CountryListItem(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val region: String,
    val officialName: String,
    val commonName: String,
    val flagPng: String?,
    val capital: String,
)