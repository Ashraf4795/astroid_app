package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import com.udacity.asteroidradar.base.utils.getNextSevenDaysFormattedDates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AsteroidDownloadWorker(context: Context, workerParameters: WorkerParameters): CoroutineWorker(context, workerParameters) {

    private val nextWeekDate = getNextSevenDaysFormattedDates()

    companion object {
        const val WORKER_NAME = "AsteroidDownloadWorker"
    }

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    @Inject
    lateinit var repository: Repository

    override suspend fun doWork():Result {
       return try {
           val remoteAsteroids = repository.getAsteroidsRemote(nextWeekDate.first(), nextWeekDate.last())
           repository.addAsteroids(remoteAsteroids.map { AsteroidEntity.convert(it) })

           Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }


}