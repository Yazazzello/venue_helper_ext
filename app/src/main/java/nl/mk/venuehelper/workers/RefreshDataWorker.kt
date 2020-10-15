package nl.mk.venuehelper.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import nl.mk.venuehelper.repositories.VenueRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class RefreshDataWorker(
    appContext: Context, workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters), KoinComponent {
    override suspend fun doWork(): Result {
        val result = kotlin.runCatching {
            val repository: VenueRepository by inject()
            repository.refreshExpiredData()
        }.onFailure {
            Timber.e(it)
        }

        return if (result.isFailure) {
            Result.failure()
        } else {
            Result.success()
        }
    }
}