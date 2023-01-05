package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call

interface Repository {
    suspend fun getAsteroids(startData: String, endDate: String): List<Asteroid>
    suspend fun getAsteroidsRemote(startData: String, endDate: String): List<Asteroid>

    suspend fun getPictureOfDay(): PictureOfDayEntity

    suspend fun getPictureOfTheDayRemote(): PictureOfDay
    suspend fun getPictureOfTheDayLocal(): PictureOfDayEntity?

    suspend fun addAsteroids(asteroids: List<AsteroidEntity>)
    suspend fun addPictureOfDay(pictureOfDayEntity: PictureOfDayEntity)

    suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity)
    suspend fun deletePictureOfDay()
    suspend fun refreshAsteroidApiContent(
        remoteAsteroids: List<Asteroid>,
        remotePictureOfDay: PictureOfDay
    )
}