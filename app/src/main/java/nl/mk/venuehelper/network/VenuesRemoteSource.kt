package nl.mk.venuehelper.network

import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.database.models.Venues
import nl.mk.venuehelper.shared.toDbModel

interface VenuesRemoteSource {
    suspend fun getVenues(locationName: String): Venues
    suspend fun getVenueDetails(venueId: String): VenueDb
}

class VenuesRemoteSourceImpl(
    private val foursquareService: FoursquareService
) : VenuesRemoteSource {

    override suspend fun getVenues(locationName: String): Venues {
        return foursquareService
            .getVenuesByLocation(locationName)
            .response
            .venues
            .map { venuePreview -> venuePreview.toDbModel() }
    }

    override suspend fun getVenueDetails(venueId: String): VenueDb {
        return foursquareService
            .getVenueDetails(venueId)
            .response
            .venue.toDbModel()
    }
}