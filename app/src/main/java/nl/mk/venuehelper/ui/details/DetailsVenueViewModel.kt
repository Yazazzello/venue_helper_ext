package nl.mk.venuehelper.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.repositories.VenueRepository
import nl.mk.venuehelper.shared.Resource

class DetailsVenueViewModel(
    private val venueRepository: VenueRepository
) : ViewModel() {

    fun requestVenueDetails(venue: VenueDb): LiveData<Resource<VenueDb>> {
        return venueRepository.getDetails(venue).asLiveData()
    }
}