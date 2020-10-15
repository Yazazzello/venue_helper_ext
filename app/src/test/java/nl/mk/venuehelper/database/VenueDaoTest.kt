package nl.mk.venuehelper.database

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import nl.mk.venuehelper.database.models.VenueDb
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class VenueDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: VenuesDao

    @Before
    fun setup() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.venuesDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertVenue() = runBlocking {
        val element = VenueDb("id", title = "some title", address = "some straat", place = "City")
        dao.insert(listOf(element))

        val result = dao.findVenueById("id")

        // Assertions
        Assert.assertEquals(element, result)
    }
}