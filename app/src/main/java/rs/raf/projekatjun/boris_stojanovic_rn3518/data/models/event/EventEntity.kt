package rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title : String,
    val description: String,
    val location: String,
    val priority: String,
    val url: String,
    @ColumnInfo(name = "event_date")val eventDate: Date,
    @ColumnInfo(name = "event_time")val eventTime: Date
)