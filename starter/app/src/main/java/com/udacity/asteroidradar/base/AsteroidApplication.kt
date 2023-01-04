package com.udacity.asteroidradar.base

import android.app.Application
import com.udacity.asteroidradar.base.data.worker.AsteroidWorkerInitializer
import com.udacity.asteroidradar.base.di.AppComponent
import com.udacity.asteroidradar.base.di.AppModule
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AsteroidApplication: Application() {
    private lateinit var appComponent: AppComponent
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        // delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            AsteroidWorkerInitializer.initialize(this@AsteroidApplication)
        }
    }

    fun appComponent() = this.appComponent
}