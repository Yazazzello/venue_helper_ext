package nl.mk.venuehelper.network.models


import com.google.gson.annotations.SerializedName

data class VenueDetailsResponse(
    @SerializedName("meta")
    var meta: Meta?,
    @SerializedName("response")
    var response: Response
) {
    data class Meta(
        @SerializedName("code")
        var code: Int?,
        @SerializedName("requestId")
        var requestId: String?
    )

    data class Response(
        @SerializedName("venue")
        var venue: Venue
    ) {
        data class Venue(
            @SerializedName("bestPhoto")
            var bestPhoto: BestPhoto?,
            @SerializedName("contact")
            var contact: Contact?,
            @SerializedName("description")
            var description: String?,
            @SerializedName("id")
            var id: String,
            @SerializedName("location")
            var location: Location,
            @SerializedName("name")
            var name: String,
            @SerializedName("photos")
            var photos: Photos?,
            @SerializedName("rating")
            var rating: Double,
            @SerializedName("url")
            var url: String?,
            @SerializedName("verified")
            var verified: Boolean?
        ) {
            data class BestPhoto(
                @SerializedName("createdAt")
                var createdAt: Int?,
                @SerializedName("height")
                var height: Int?,
                @SerializedName("id")
                var id: String?,
                @SerializedName("prefix")
                var prefix: String?,
                @SerializedName("suffix")
                var suffix: String?,
                @SerializedName("visibility")
                var visibility: String?,
                @SerializedName("width")
                var width: Int?
            )

            data class Contact(
                @SerializedName("facebook")
                var facebook: String?,
                @SerializedName("facebookName")
                var facebookName: String?,
                @SerializedName("facebookUsername")
                var facebookUsername: String?,
                @SerializedName("formattedPhone")
                var formattedPhone: String?,
                @SerializedName("instagram")
                var instagram: String?,
                @SerializedName("phone")
                var phone: String?,
                @SerializedName("twitter")
                var twitter: String?
            )

            data class Location(
                @SerializedName("address")
                var address: String?,
                @SerializedName("cc")
                var cc: String?,
                @SerializedName("city")
                var city: String,
                @SerializedName("country")
                var country: String?,
                @SerializedName("crossStreet")
                var crossStreet: String?,
                @SerializedName("formattedAddress")
                var formattedAddress: List<String>,
                @SerializedName("lat")
                var lat: Double?,
                @SerializedName("lng")
                var lng: Double?,
                @SerializedName("postalCode")
                var postalCode: String?,
                @SerializedName("state")
                var state: String?
            )

            data class Photos(
                @SerializedName("count")
                var count: Int?,
                @SerializedName("groups")
                var groups: List<Group?>?
            ) {
                data class Group(
                    @SerializedName("count")
                    var count: Int?,
                    @SerializedName("items")
                    var items: List<Item?>?,
                    @SerializedName("name")
                    var name: String?,
                    @SerializedName("type")
                    var type: String?
                ) {
                    data class Item(
                        @SerializedName("createdAt")
                        var createdAt: Int?,
                        @SerializedName("height")
                        var height: Int?,
                        @SerializedName("id")
                        var id: String?,
                        @SerializedName("prefix")
                        var prefix: String?,
                        @SerializedName("source")
                        var source: Source?,
                        @SerializedName("suffix")
                        var suffix: String?,
                        @SerializedName("user")
                        var user: User?,
                        @SerializedName("visibility")
                        var visibility: String?,
                        @SerializedName("width")
                        var width: Int?
                    ) {
                        data class Source(
                            @SerializedName("name")
                            var name: String?,
                            @SerializedName("url")
                            var url: String?
                        )

                        data class User(
                            @SerializedName("firstName")
                            var firstName: String?,
                            @SerializedName("id")
                            var id: String?,
                            @SerializedName("lastName")
                            var lastName: String?
                        )
                    }
                }
            }

        }
    }
}