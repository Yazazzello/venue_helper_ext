package nl.mk.venuehelper.di

import nl.mk.venuehelper.repositories.VenueRepository
import nl.mk.venuehelper.repositories.VenueRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<VenueRepository> {
        VenueRepositoryImpl(remoteSource = get(), venuesDao = get(), dispatchers = get())
    }

}
