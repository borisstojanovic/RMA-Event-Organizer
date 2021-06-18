package rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.boris_stojanovic_rn3518.data.datasources.local.converters.DateConverter
import rs.raf.projekat2.boris_stojanovic_rn3518.data.datasources.local.converters.StringListConverter
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.EventEntity
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.LocationEntity

@Database(
    entities = [EventEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class EventDataBase : RoomDatabase() {
    abstract fun getEventDao(): EventsDao
    abstract fun getLocationDao(): LocationsDao
}