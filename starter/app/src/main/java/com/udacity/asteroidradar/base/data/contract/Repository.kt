package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay

interface Repository {
    suspend fun getAsteroids(offline: Boolean = true): List<Asteroid>
    suspend fun getPictureOfTheDay(offline: Boolean = true): PictureOfDay
}