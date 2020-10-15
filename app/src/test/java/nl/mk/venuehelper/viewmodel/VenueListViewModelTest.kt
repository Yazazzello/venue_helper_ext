package nl.mk.venuehelper.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import nl.mk.venuehelper.repositories.VenueRepository
import nl.mk.venuehelper.shared.Resource
import nl.mk.venuehelper.ui.list.VenuesListViewModel
import nl.mk.venuehelper.utils.CoroutineTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VenueListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: VenuesListViewModel

    @RelaxedMockK
    lateinit var repository: VenueRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun venueList_returnsEmpty() = coroutineTestRule.runBlockingTest {
        // Stub repository
        val emptyValue = Resource.empty()
        val flow = flow { emit(emptyValue) }
        every { repository.getVenuesByLocation(any()) } returns flow

        // Call ViewModel
        viewModel = VenuesListViewModel(repository)
        viewModel.setQuery("test")
        viewModel.venues.observeForever {}
//        viewModel.venues.getOrAwaitValue()
        advanceTimeBy(600)
        Assert.assertEquals(emptyValue, viewModel.venues.value)
//        Assert.assertEquals(emptyValue, viewModel.venues.getOrAwaitValue())
    }
}