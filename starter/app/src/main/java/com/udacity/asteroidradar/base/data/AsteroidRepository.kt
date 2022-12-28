package com.udacity.asteroidradar.base.data

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay

class AsteroidRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getAsteroids(offline: Boolean): List<Asteroid> {
        return if (offline) {
            localDataSource.getAsteroids()
        } else {
            remoteDataSource.getAsteroids()
        }
    }

    override suspend fun getPictureOfTheDay(offline: Boolean): PictureOfDay {
        return if (offline) {
            localDataSource.getPictureOfTheDay()
        } else {
            remoteDataSource.getPictureOfTheDay()
        }
    }
}