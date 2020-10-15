package nl.mk.venuehelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.mk.venuehelper.database.models.VenueDb

private const val DATABASE_NAME = "venues.db"

@Database(entities = [VenueDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun venuesDao(): VenuesDao

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
