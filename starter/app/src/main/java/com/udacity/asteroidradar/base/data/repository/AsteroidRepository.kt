package com.udacity.asteroidradar.base.data.repository

import com.udacity.asteroidradar.base.data.contract.LocalDataSource
import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import com.udacity.asteroidradar.base.utils.getTodayDate
import com.udacity.asteroidradar.base.utils.getYesterdayDate
import javax.inject.Inject

class AsteroidRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    /*
    check for local cached asteroids data, if not exists, fetch from server and cache for next usage.
     */
    override suspend fun getAsteroids(startData: String, endDate: String): List<Asteroid> {
        val localAsteroids = localDataSource.getAsteroids()
        if (localAsteroids.isNotEmpty()) {
            return localAsteroids.map { Asteroid.convert(it)}.filter { it.closeApproachDate != getYesterdayDate() }
        } else {
            val remoteAsteroids = remoteDataSource.getAsteroids(startData, endDate)
            saveAsteroids(remoteAsteroids)
            return remoteAsteroids
        }
    }

    override suspend fun getAsteroidsRemote(startData: String, endDate: String): List<Asteroid> {
        return remoteDataSource.getAsteroids(startData, endDate)
    }

    override suspend fun getPictureOfDay(): PictureOfDayEntity {
        val localPicture = getPictureOfTheDayLocal()
        if (localPicture != null) {
            if (localPicture.date != getTodayDate()) {
                return PictureOfDayEntity.convert(getRemotePictureOfDayAndSave())
            }
            return localPicture
        } else {
            val remotePictureOfDay = getRemotePictureOfDayAndSave()
            return PictureOfDayEntity.convert(remotePictureOfDay)
        }
    }

    private suspend fun getRemotePictureOfDayAndSave(): PictureOfDay {
        val remotePictureOfDay = getPictureOfTheDayRemote()
        savePictureOfDay(remotePictureOfDay)
        return remotePictureOfDay
    }

    private suspend fun savePictureOfDay(remotePictureOfDay: PictureOfDay) {
        val pictureOfDayEntity = PictureOfDayEntity.convert(pictureOfDay = remotePictureOfDay)
        deletePictureOfDay()
        addPictureOfDay(pictureOfDayEntity)
    }

    override suspend fun getPictureOfTheDayRemote(): PictureOfDay {
        return remoteDataSource.getPictureOfTheDay()
    }

    override suspend fun getPictureOfTheDayLocal(): PictureOfDayEntity?{
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

    override suspend fun refreshAsteroidApiContent(
        remoteAsteroids: List<Asteroid>,
        remotePictureOfDay: PictureOfDay
    ) {
        deletePictureOfDay()
        addPictureOfDay(PictureOfDayEntity.convert(remotePictureOfDay))

        localDataSource.deleteAsteroidByDate(getTodayDate())
        addAsteroids(remoteAsteroids.map { AsteroidEntity.convert(it) })
    }

    private suspend fun saveAsteroids(asteroidsResponse: List<Asteroid>) {
        val asteroidsEntities = asteroidsResponse.map { AsteroidEntity.convert(it) }
        addAsteroids(asteroidsEntities)
    }

}