package rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local.LocationsDao
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.remote.LocationService
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.Resource
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.Location
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.LocationEntity
import timber.log.Timber

class LocationRepositoryImpl(
    private val remoteDataSource: LocationService,
    private val localDataSource: LocationsDao
) : LocationRepository {

    override fun fetch(city: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .get(city)
            .doOnNext {
                val entity = LocationEntity(
                    it.datetime,
                    it.timezone,
                    it.abbreviation
                )
                localDataSource.insert(entity).blockingAwait()
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Location>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Location(
                        it.datetime,
                        it.timezone
                    )
                }
            }
    }

    override fun getByTimezone(timezone: String): Observable<Location> {
        return localDataSource
            .getByTimezone(timezone)
            .map {
                Location(
                    it.datetime,
                    it.timezone
                )
            }
    }

}