package com.udacity.asteroidradar.base

import android.app.Application
import com.udacity.asteroidradar.base.di.AppComponent
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import dagger.Component

class AsteroidApplication: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun appComponent() = this.appComponent
}