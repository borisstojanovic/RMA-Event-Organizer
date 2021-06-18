package rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.EventEntity

@Dao
abstract class EventsDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: EventEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<EventEntity>): Completable

    @Query("SELECT * FROM event")
    abstract fun getAll(): Observable<List<EventEntity>>

    @Query("SELECT * FROM event WHERE id = :id")
    abstract fun getById(id: Int): Observable<EventEntity>

    @Query("DELETE FROM event")
    abstract fun deleteAll()

    @Delete
    abstract fun delete(eventEntity: EventEntity): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<EventEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Update
    abstract fun update(eventEntity: EventEntity)

    @Query("SELECT * FROM event WHERE title LIKE '%' || :title || '%'")
    abstract fun getByCategory(title: String): Observable<List<EventEntity>>
}