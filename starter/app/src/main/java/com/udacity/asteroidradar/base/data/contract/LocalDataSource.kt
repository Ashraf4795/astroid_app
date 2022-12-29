package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity

interface LocalDataSource {
    suspend fun getAsteroids(): List<AsteroidEntity>
    suspend fun getPictureOfTheDay(): PictureOfDayEntity

    suspend fun addAsteroids(asteroids: List<AsteroidEntity>)
    suspend fun addPictureOfDay(pictureOfDayEntity: PictureOfDayEntity)

    suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity)
    suspend fun deletePictureOfDay()
}