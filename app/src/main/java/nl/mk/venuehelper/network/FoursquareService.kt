package nl.mk.venuehelper.network

import nl.mk.venuehelper.BuildConfig
import nl.mk.venuehelper.network.models.VenueDetailsResponse
import nl.mk.venuehelper.network.models.VenuesPreviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareService {

    @GET("venues/search?limit=10&radius=10000" +
            "&v=20201001&client_id=${BuildConfig.F_CLIENT_ID}" +
            "&client_secret=${BuildConfig.F_CLIENT_SECRET}")
    suspend fun getVenuesByLocation(@Query("near") place: String): VenuesPreviewResponse

    @GET("venues/{venueId}/" +
            "?v=20201001&client_id=${BuildConfig.F_CLIENT_ID}" +
            "&client_secret=${BuildConfig.F_CLIENT_SECRET}")
    suspend fun getVenueDetails(@Path("venueId") venueId: String): VenueDetailsResponse
}