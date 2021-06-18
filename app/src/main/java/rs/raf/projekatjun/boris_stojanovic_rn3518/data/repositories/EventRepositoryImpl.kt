package rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local.EventsDao
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.EventEntity

class EventRepositoryImpl(
    private val localDataSource: EventsDao,
) : EventRepository {

    override fun getAll(): Observable<List<Event>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Event(
                        it.id,
                        it.title,
                        it.description,
                        it.location,
                        it.priority,
                        it.url,
                        it.eventDate
                    )
                }
            }
    }

    override fun insert(event: Event): Completable {
        val eventEntity =
            EventEntity(
                event.id,
                event.title,
                event.description,
                event.location,
                event.priority,
                event.url,
                event.eventDate
            )
        return localDataSource
            .insert(eventEntity)
    }

    override fun delete(event: Event): Completable {
        val eventEntity =
            EventEntity(
                event.id,
                event.title,
                event.description,
                event.location,
                event.priority,
                event.url,
                event.eventDate
            )
        return localDataSource
            .delete(eventEntity)
    }

}