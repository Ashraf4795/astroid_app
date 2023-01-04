package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.base.data.worker.AsteroidDownloadWorker.Companion.WORKER_NAME
import java.util.concurrent.TimeUnit

object AsteroidWorkerInitializer {
    private lateinit var workManager: WorkManager
    private val constrains = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .build()

    private val asteroidsRefreshRequest = PeriodicWorkRequestBuilder<AsteroidDownloadWorker>(
        1, TimeUnit.DAYS,
    ).apply {
        addTag("sync_asteroid_worker_tag")
        setInitialDelay(10, TimeUnit.MINUTES).setConstraints(constrains)
    }.build()

    fun initialize(context: Context) {
        workManager = WorkManager.getInstance(context)
        enqueue()
    }

    fun enqueue() {
        workManager.enqueueUniquePeriodicWork(
            WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            asteroidsRefreshRequest
        )
    }
}