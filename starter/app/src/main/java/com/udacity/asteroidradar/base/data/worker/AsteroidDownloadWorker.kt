package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class AsteroidDownloadWorker(context: Context, workerParameters: WorkerParameters): CoroutineWorker(context, workerParameters) {

    companion object {
        const val WORKER_NAME = "AsteroidDownloadWorker"
    }


    override suspend fun doWork(): Result {
       return try {
            //val asteroids = repository.getAsteroidsLocal()
            Log.d("worker", "asteroids.toString()")
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }
}