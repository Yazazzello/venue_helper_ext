package nl.mk.venuehelper.network.models

import com.google.gson.annotations.SerializedName

data class VenuesPreviewResponse(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: Response
)

data class Meta(
    @SerializedName("code") val code: Int,
    @SerializedName("requestId") val requestId: String
)

data class Response(
    @SerializedName("venues") val venues: List<VenuePreview>,
    @SerializedName("confident") val confident: Boolean
)

data class VenuePreview(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("location") val location: LocationPreview
)

data class LocationPreview(
    @SerializedName("address") val address: String,
    @SerializedName("cc") val cc: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?,
    @SerializedName("country") val country: String,
    @SerializedName("formattedAddress") val formattedAddress: List<String>
)