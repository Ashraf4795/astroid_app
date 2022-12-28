package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay

interface LocalDataSource {
    suspend fun getAsteroids(): List<Asteroid>
    suspend fun getPictureOfTheDay(): PictureOfDay
}