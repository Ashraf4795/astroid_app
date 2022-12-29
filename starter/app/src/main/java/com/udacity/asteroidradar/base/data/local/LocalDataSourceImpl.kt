package com.udacity.asteroidradar.base.data.local

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor (): LocalDataSource {
    override suspend fun getAsteroids(): List<Asteroid> {
        return emptyList()
    }

    override suspend fun getPictureOfTheDay(): PictureOfDay {
        return PictureOfDay("", "", "")
    }
}