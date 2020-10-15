package nl.mk.venuehelper.shared

import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.network.models.VenueDetailsResponse
import nl.mk.venuehelper.network.models.VenuePreview


fun VenueDetailsResponse.Response.Venue.toDbModel(): VenueDb {
    return VenueDb(
        venueId = this.id,
        place = this.location.city,
        title = this.name,
        description = this.description,
        address = this.location.formattedAddress.joinToString(separator = ","),
        rating = this.rating,
        photoUrl = this.bestPhoto.parseToUrl(),
        updateTime = System.currentTimeMillis()
    )
}

fun VenueDetailsResponse.Response.Venue.BestPhoto?.parseToUrl(): String? {
    this ?: return null
    return with(this) {
        "${prefix}${width}x${height}${suffix}"
    }
}

fun VenuePreview.toDbModel(): VenueDb {
    return VenueDb(
        venueId = this.id,
        place = this.location.city,
        title = this.name,
        address = this.location.formattedAddress.joinToString(separator = ",")
    )
}