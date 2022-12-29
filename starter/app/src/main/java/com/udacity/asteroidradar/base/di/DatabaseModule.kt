package com.udacity.asteroidradar.base.di

import android.content.Context
import androidx.room.Room
import com.udacity.asteroidradar.base.data.local.room.AppDatabase
import com.udacity.asteroidradar.base.data.local.room.dao.AsteroidDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoom(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "ASTEROID_RADAR_DATABASE"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAsteroidDao(appDatabase: AppDatabase): AsteroidDao {
        return appDatabase.asteroidDao()
    }
}