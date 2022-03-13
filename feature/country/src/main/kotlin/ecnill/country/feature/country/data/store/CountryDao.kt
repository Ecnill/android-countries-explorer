package ecnill.country.feature.country.data.store

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ecnill.country.feature.country.model.CountryDetailItem
import ecnill.country.feature.country.model.CountryListItem

@Dao
internal interface CountryDao {

    @Query("SELECT * FROM country WHERE region = :region")
    fun getCountries(region: String): List<CountryListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryListItem>)

    @Query("SELECT * FROM countryDetail WHERE officialName = :country")
    fun getDetail(country: String): CountryDetailItem?

    @Insert
    fun insertDetail(country: CountryDetailItem)
}
