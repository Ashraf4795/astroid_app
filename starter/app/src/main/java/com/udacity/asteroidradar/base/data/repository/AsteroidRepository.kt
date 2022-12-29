package com.udacity.asteroidradar.base.data.repository

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import javax.inject.Inject

class AsteroidRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getAsteroidsRemote(startData: String, endDate: String): List<Asteroid> {
        return remoteDataSource.getAsteroids(startData, endDate)
    }

    override suspend fun getPictureOfTheDayRemote(apiKey: String): PictureOfDay {
        return remoteDataSource.getPictureOfTheDay(apiKey)
    }

    override suspend fun getAsteroidsLocal(): List<AsteroidEntity> {
        return localDataSource.getAsteroids()
    }

    override suspend fun getPictureOfTheDayLocal(): PictureOfDayEntity{
        return localDataSource.getPictureOfTheDay()
    }

    override suspend fun addAsteroids(asteroids: List<AsteroidEntity>) {
        localDataSource.addAsteroids(asteroids)
    }

    override suspend fun addPictureOfDay(pictureOfDayEntity: PictureOfDayEntity) {
        localDataSource.addPictureOfDay(pictureOfDayEntity)
    }

    override suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity) {
        localDataSource.deleteAsteroid(asteroidEntity)
    }

    override suspend fun deletePictureOfDay() {
        localDataSource.deletePictureOfDay()
    }

}