package nl.mk.venuehelper.repositories

import kotlinx.coroutines.flow.*
import nl.mk.venuehelper.contextprovider.DispatcherProvider
import nl.mk.venuehelper.database.VenuesDao
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.database.models.Venues
import nl.mk.venuehelper.network.VenuesRemoteSource
import nl.mk.venuehelper.shared.Resource
import timber.log.Timber

private const val CACHE_EXPIRATION: Long = 24*60*60*1000 //1 DAY

interface VenueRepository {
    fun getVenuesByLocation(locationName: String): Flow<Resource<Venues>>
    fun getDetails(venue: VenueDb): Flow<Resource<VenueDb>>
    suspend fun refreshExpiredData()
}

class VenueRepositoryImpl(
    private val remoteSource: VenuesRemoteSource,
    private val venuesDao: VenuesDao,
    private val dispatchers: DispatcherProvider
) : VenueRepository {

    override fun getVenuesByLocation(locationName: String): Flow<Resource<Venues>> = flow {

        emit(Resource.loading())
        kotlin.runCatching {
            //check db
            var resultList: List<VenueDb>? = venuesDao.search(locationName)
            if (resultList.isNullOrEmpty()) {
                //load from rest api
                resultList = remoteSource.getVenues(locationName)
                venuesDao.insert(resultList)
            }
            resultList
        }.onSuccess {
            emit(Resource.successOrEmpty(it!!))
        }.onFailure {
            emit(Resource.error(it))
        }

    }.flowOn(dispatchers.io)

    override fun getDetails(venue: VenueDb): Flow<Resource<VenueDb>> = flow {
        emit(Resource.loading())
        kotlin.runCatching {

            //check db
            var tempVenue = venuesDao.findVenueById(venue.venueId)
            if (tempVenue?.updateTime == null) {
                //load from rest api
                tempVenue = remoteSource.getVenueDetails(venue.venueId)
                with(venue) {
                    place = tempVenue.place
                    description = tempVenue.description
                    rating = tempVenue.rating
                    photoUrl = tempVenue.photoUrl
                    updateTime = tempVenue.updateTime
                }
                venuesDao.update(venue)
            }
            venue
        }.onSuccess {
            emit(Resource.Success(it))
        }.onFailure {
            emit(Resource.error(it))
        }
    }.flowOn(dispatchers.io)

    override suspend fun refreshExpiredData() {
        val expiredTs = System.currentTimeMillis() - CACHE_EXPIRATION
        venuesDao
            .getAllExpiredVenues(expiredTs)
            .map { expiredVenue ->
                val tempVenue = remoteSource.getVenueDetails(expiredVenue.venueId)
                Timber.d("refreshing $expiredVenue")
                with(expiredVenue) {
                    place = tempVenue.place
                    description = tempVenue.description
                    rating = tempVenue.rating
                    photoUrl = tempVenue.photoUrl
                    updateTime = tempVenue.updateTime
                }
                venuesDao.update(expiredVenue)
            }
    }
}