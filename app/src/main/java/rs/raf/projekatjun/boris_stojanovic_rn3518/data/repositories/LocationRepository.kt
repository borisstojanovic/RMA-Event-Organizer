package rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories

import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.Resource
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.Location

interface LocationRepository {
    fun fetch(city: String): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Location>>
    fun getByTimezone(timezone: String): Observable<Location>
}