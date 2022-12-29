package com.udacity.asteroidradar.base.di

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.local.LocalDataSourceImpl
import com.udacity.asteroidradar.base.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class DataSourceModule {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}