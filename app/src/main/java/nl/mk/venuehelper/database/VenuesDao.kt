package nl.mk.venuehelper.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.database.models.Venues

@Dao
interface VenuesDao {

    @Query("SELECT * FROM venues WHERE ts <=:expiredTs")
    fun getAllExpiredVenues(expiredTs: Long): Venues

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venues: Venues)

    @Query("SELECT * FROM venues WHERE place LIKE :query")
    suspend fun search(query: String): Venues

    @Query("SELECT * FROM venues WHERE venueId LIKE :venueId")
    suspend fun findVenueById(venueId: String): VenueDb?

    @Update
    suspend fun update(venue: VenueDb)

}
