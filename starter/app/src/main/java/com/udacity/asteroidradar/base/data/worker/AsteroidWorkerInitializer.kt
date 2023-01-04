package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import android.os.Build
import androidx.work.*
import java.util.concurrent.TimeUnit

object AsteroidWorkerInitializer {
    private val constrains = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }
        .build()

    private val asteroidsRefreshRequest = PeriodicWorkRequestBuilder<AsteroidDownloadWorker>(
        1,
        TimeUnit.DAYS,
    ).setConstraints(constrains).build()

    fun initialize(context: Context) {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            AsteroidDownloadWorker.WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            asteroidsRefreshRequest
        )
    }
}