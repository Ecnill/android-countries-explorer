package ecnill.country.feature.country.data.store

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ecnill.country.feature.country.model.CountryDetailItem
import ecnill.country.feature.country.model.CountryListItem

@Database(entities = [CountryListItem::class, CountryDetailItem::class], version = 1)
@TypeConverters(
    DatabaseConverters.CountryList::class,
    DatabaseConverters.CountryDetail::class,
    DatabaseConverters.ListString::class
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}