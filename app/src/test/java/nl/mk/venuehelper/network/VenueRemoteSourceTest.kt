package nl.mk.venuehelper.network

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import nl.mk.venuehelper.network.models.*
import nl.mk.venuehelper.utils.CoroutineTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VenueRemoteSourceTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var remote: VenuesRemoteSourceImpl

    @MockK
    lateinit var service: FoursquareService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        remote = VenuesRemoteSourceImpl(service)
    }

    @Test
    fun venueGetPreview() = coroutineTestRule.runBlockingTest {
        // Stub service
        val response = VenuesPreviewResponse(
            Meta(200, ""),
            Response(
                listOf(
                    VenuePreview(
                        "id", "name", LocationPreview(
                            address = "some",
                            cc = "NL",
                            city = "Hil",
                            state = null,
                            country = "NL",
                            formattedAddress = listOf("streets")
                        )
                    )
                ), true
            )
        )
        coEvery { service.getVenuesByLocation(any()) } returns response

        // Call remote
        val venues = remote.getVenues("some")

        // Assertions
        Assert.assertEquals(1, venues.size)
        Assert.assertEquals("id", venues[0].venueId)
    }
}