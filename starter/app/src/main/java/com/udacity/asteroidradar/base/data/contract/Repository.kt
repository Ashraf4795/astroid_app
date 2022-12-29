package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call

interface Repository {
    suspend fun getAsteroidsRemote(startData: String, endDate: String): List<Asteroid>
    suspend fun getAsteroidsLocal(): List<AsteroidEntity>
    suspend fun getPictureOfTheDayRemote(apiKey: String): PictureOfDay
    suspend fun getPictureOfTheDayLocal(): PictureOfDayEntity

    suspend fun addAsteroids(asteroids: List<AsteroidEntity>)
    suspend fun addPictureOfDay(pictureOfDayEntity: PictureOfDayEntity)

    suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity)
    suspend fun deletePictureOfDay()
}