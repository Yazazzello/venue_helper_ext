package nl.mk.venuehelper.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.*
import nl.mk.venuehelper.database.models.Venues
import nl.mk.venuehelper.repositories.VenueRepository
import nl.mk.venuehelper.shared.Resource

private const val DELAY_MS = 500L
private const val MIN_CHARS = 3

class VenuesListViewModel(
    private val venueRepository: VenueRepository
) : ViewModel() {

    private val mutableQuery = MutableStateFlow("")

    val venues: LiveData<Resource<Venues>> = mutableQuery
        .debounce(DELAY_MS)
        .filter { query -> query.isNotEmpty() && query.length >= MIN_CHARS }
        .flatMapLatest { query ->
            venueRepository.getVenuesByLocation(query)
        }
        .asLiveData()

    fun setQuery(query: String) {
        mutableQuery.value = query
    }
}