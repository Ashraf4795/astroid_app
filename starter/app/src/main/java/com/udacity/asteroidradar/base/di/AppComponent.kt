package com.udacity.asteroidradar.base.di

import android.content.Context
import android.provider.ContactsContract
import com.udacity.asteroidradar.base.data.worker.AsteroidDownloadWorker
import com.udacity.asteroidradar.feature.detail.DetailFragment
import com.udacity.asteroidradar.feature.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailFragment)
    fun inject(worker: AsteroidDownloadWorker)
}