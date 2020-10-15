package nl.mk.venuehelper.di

import nl.mk.venuehelper.contextprovider.AppDispatcherProvider
import nl.mk.venuehelper.contextprovider.DispatcherProvider
import nl.mk.venuehelper.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single<DispatcherProvider> { AppDispatcherProvider() }

    single { AppDatabase.getDatabase(androidApplication()) }

    single { get<AppDatabase>().venuesDao() }

}