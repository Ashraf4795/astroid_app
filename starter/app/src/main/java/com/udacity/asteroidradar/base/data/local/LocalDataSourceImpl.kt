package com.udacity.asteroidradar.base.data.local

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay

class LocalDataSourceImpl: LocalDataSource {
    override suspend fun getAsteroids(): List<Asteroid> {
        return emptyList()
    }

    override suspend fun getPictureOfTheDay(): PictureOfDay {
        return PictureOfDay("", "", "")
    }
}