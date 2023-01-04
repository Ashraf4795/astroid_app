package com.udacity.asteroidradar.base.data.local

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.local.room.dao.AsteroidDao
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val asteroidDao: AsteroidDao
) : LocalDataSource {
    override suspend fun getAsteroids(): List<AsteroidEntity> {
        return asteroidDao.getAsteroids()
    }

    override suspend fun getPictureOfTheDay(): PictureOfDayEntity? {
        return asteroidDao.getPictureOfDay()
    }

    override suspend fun addAsteroids(asteroids: List<AsteroidEntity>) {
        asteroidDao.insertAsteroids(asteroids)
    }

    override suspend fun addPictureOfDay(pictureOfDayEntity: PictureOfDayEntity) {
        asteroidDao.insertPictureOfDay(pictureOfDayEntity)
    }

    override suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity) {
        asteroidDao.deleteAsteroid(asteroidEntity)
    }

    override suspend fun deletePictureOfDay() {
        asteroidDao.deletePictureOfDay()
    }
}