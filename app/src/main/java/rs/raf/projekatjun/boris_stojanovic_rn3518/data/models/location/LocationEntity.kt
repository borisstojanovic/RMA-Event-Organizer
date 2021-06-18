package rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "location")
data class LocationEntity(
    val datetime: Date,

    @PrimaryKey(autoGenerate = false)
    val timezone: String,

    val abbreviation: String
)