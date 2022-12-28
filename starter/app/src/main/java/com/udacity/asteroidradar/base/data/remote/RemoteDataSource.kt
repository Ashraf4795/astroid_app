package com.udacity.asteroidradar.base.data.remote

import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidService

class RemoteDataSource(private val asteroidService: AsteroidService):
    RemoteDataSource {
    override suspend fun getAsteroids(): List<Asteroid> {
        TODO("Not yet implemented")
    }

    override suspend fun getPictureOfTheDay(): PictureOfDay {
        TODO("Not yet implemented")
    }
}