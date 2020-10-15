package nl.mk.venuehelper.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "venues")
@Parcelize
data class VenueDb(
    @PrimaryKey val venueId: String,
    @ColumnInfo(name = "place") var place: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "rating") var rating: Double? = null,
    @ColumnInfo(name = "photoUrl") var photoUrl: String? = null,
    @ColumnInfo(name = "ts") var updateTime: Long? = null
) : Parcelable

typealias Venues = List<VenueDb>