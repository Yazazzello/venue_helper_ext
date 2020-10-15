package nl.mk.venuehelper.di

import nl.mk.venuehelper.ui.details.DetailsVenueViewModel
import nl.mk.venuehelper.ui.list.VenuesListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { VenuesListViewModel(venueRepository = get()) }

    viewModel { DetailsVenueViewModel(venueRepository = get()) }

}
