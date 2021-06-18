package rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event

import java.util.*

data class Event(
    val id: Long,
    val title : String,
    val description: String,
    val location: String,
    val priority: String,
    val url: String,
    val eventDate: Date,
)