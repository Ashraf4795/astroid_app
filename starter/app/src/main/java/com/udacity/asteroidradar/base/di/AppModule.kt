package com.udacity.asteroidradar.base.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context = context
}