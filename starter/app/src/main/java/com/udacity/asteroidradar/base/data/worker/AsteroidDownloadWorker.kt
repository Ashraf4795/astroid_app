package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import com.udacity.asteroidradar.base.utils.getNextSevenDaysFormattedDates
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
           val remotePictureOfDay = repository.getPictureOfTheDayRemote()

           repository.refreshAsteroidApiContent(remoteAsteroids, remotePictureOfDay)

           Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }


}