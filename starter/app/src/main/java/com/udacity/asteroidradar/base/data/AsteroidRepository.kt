package com.udacity.asteroidradar.base.data

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.contract.Repository
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

    override suspend fun getAsteroidsLocal(): List<Asteroid> {
        return localDataSource.getAsteroids()
    }

    override suspend fun getPictureOfTheDayLocal(): PictureOfDay {
        return localDataSource.getPictureOfTheDay()
    }

}