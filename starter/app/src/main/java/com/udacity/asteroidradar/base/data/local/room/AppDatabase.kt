package com.udacity.asteroidradar.base.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.base.data.local.room.dao.AsteroidDao
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity

@Database(entities = [AsteroidEntity::class, PictureOfDayEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao
}