package rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.LocationResponse

interface LocationService {

    @GET("{city}")
    fun get(@Path("city") city: String): Observable<LocationResponse>
}