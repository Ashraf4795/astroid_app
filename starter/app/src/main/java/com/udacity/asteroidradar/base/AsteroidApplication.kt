package com.udacity.asteroidradar.base

import android.app.Application
import com.udacity.asteroidradar.base.di.AppComponent
import com.udacity.asteroidradar.base.di.AppModule
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import dagger.Component

class AsteroidApplication: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun appComponent() = this.appComponent
}