package rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.LocationEntity

@Dao
abstract class LocationsDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(location: LocationEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<LocationEntity>): Completable

    @Query("SELECT * FROM location")
    abstract fun getAll(): Observable<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE timezone like :timezone")
    abstract fun getByTimezone(timezone: String): Observable<LocationEntity>

    @Query("DELETE FROM location")
    abstract fun deleteAll()

    @Delete
    abstract fun delete(locationEntity: LocationEntity)

    @Transaction
    open fun deleteAndInsertAll(entities: List<LocationEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Update
    abstract fun update(locationEntity: LocationEntity)
}