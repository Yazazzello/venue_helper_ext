package nl.mk.venuehelper

import android.app.Application
import androidx.work.*
import nl.mk.venuehelper.di.appModule
import nl.mk.venuehelper.di.networkModule
import nl.mk.venuehelper.di.repositoryModule
import nl.mk.venuehelper.di.viewModelModule
import nl.mk.venuehelper.workers.RefreshDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import java.util.concurrent.TimeUnit

class App : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                viewModelModule,
                networkModule,
                repositoryModule
            )
        }

        setupRefreshWorker()
    }

    private fun setupRefreshWorker() {
        val refreshWorkRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            24, TimeUnit.HOURS, // repeatInterval (the period cycle)
            30, TimeUnit.MINUTES  // flexInterval
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                "refresh_cache",
                ExistingPeriodicWorkPolicy.KEEP,
                refreshWorkRequest
            )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
    }
}