package com.udacity.asteroidradar.base

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.base.data.worker.AsteroidDownloadWorker
import com.udacity.asteroidradar.base.di.AppComponent
import com.udacity.asteroidradar.base.di.AppModule
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApplication: Application() {
    private lateinit var appComponent: AppComponent
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        //delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            val constrains = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }
                .build()

            val asteroidsRefreshRequest = PeriodicWorkRequestBuilder<AsteroidDownloadWorker>(
                1,
                TimeUnit.DAYS,
            ).setConstraints(constrains).build()

            WorkManager.getInstance(this@AsteroidApplication).enqueueUniquePeriodicWork(
                AsteroidDownloadWorker.WORKER_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                asteroidsRefreshRequest
            )
        }
    }

    fun appComponent() = this.appComponent
}