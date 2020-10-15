package nl.mk.venuehelper.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import nl.mk.venuehelper.database.VenuesDao
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.network.VenuesRemoteSource
import nl.mk.venuehelper.shared.Resource
import nl.mk.venuehelper.utils.CoroutineTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VenueRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: VenueRepositoryImpl

    @MockK
    lateinit var remote: VenuesRemoteSource

    @RelaxedMockK
    lateinit var dao: VenuesDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = VenueRepositoryImpl(remote, dao, coroutineTestRule.testDispatcherProvider)
    }

    @Test
    fun repositoryUseCache() = coroutineTestRule.runBlockingTest {
        // Stub dao
        val venueDb = VenueDb(
            venueId = "id",
            place = "place",
            title = "title",
            description = "descr",
            address = "address",
            rating = 10.0,
            photoUrl = null,
            updateTime = System.currentTimeMillis()
        )
        coEvery { dao.findVenueById(any()) } returns venueDb

        // Call repository
        val list = repository.getDetails(
            VenueDb(
                venueId = "id",
                title = "",
                place = "",
                address = ""
            )
        ).toList(mutableListOf())

        // Assertions
        Assert.assertEquals(Resource.Loading, list[0])
        Assert.assertTrue(list[1] is Resource.Success)
        coVerify(exactly = 0) {
            remote.getVenueDetails(any())
        }
    }
}