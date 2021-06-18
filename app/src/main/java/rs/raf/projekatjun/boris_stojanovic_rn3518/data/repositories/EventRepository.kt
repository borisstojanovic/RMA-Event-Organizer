package rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event

interface EventRepository {

    fun getAll(): Observable<List<Event>>
    fun insert(event: Event): Completable
    fun delete(event: Event): Completable

}