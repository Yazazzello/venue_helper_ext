package nl.mk.venuehelper.contextprovider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext
import java.util.concurrent.Executors

class AppDispatcherProvider : DispatcherProvider {

    override val main: CoroutineDispatcher get() = Dispatchers.Main

    override val default: CoroutineDispatcher get() = Dispatchers.Default

    override val io: CoroutineDispatcher get() = Dispatchers.IO
}
