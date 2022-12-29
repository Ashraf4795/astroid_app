package com.udacity.asteroidradar.base.di

import com.udacity.asteroidradar.base.data.AsteroidRepository
import com.udacity.asteroidradar.base.data.contract.Repository
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun bindsAsteroidRepository(repository: AsteroidRepository): Repository
}