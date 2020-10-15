package nl.mk.venuehelper.contextprovider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val main: CoroutineDispatcher

    val default: CoroutineDispatcher
    
    val io: CoroutineDispatcher

}
