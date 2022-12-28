package com.udacity.asteroidradar.base.data.local

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay

class LocalDataSource: LocalDataSource {
    override suspend fun getAsteroids(): List<Asteroid> {
        TODO("Not yet implemented")
    }

    override suspend fun getPictureOfTheDay(): PictureOfDay {
        TODO("Not yet implemented")
    }
}