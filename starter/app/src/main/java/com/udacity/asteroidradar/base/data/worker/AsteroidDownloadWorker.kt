package com.udacity.asteroidradar.base.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.di.DaggerAppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AsteroidDownloadWorker(context: Context, workerParameters: WorkerParameters): CoroutineWorker(context, workerParameters) {

    companion object {
        const val WORKER_NAME = "AsteroidDownloadWorker"
    }

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    @Inject
    lateinit var repository: Repository

    override suspend fun doWork()= withContext(Dispatchers.IO) {
       try {
            //val asteroids = repository.getAsteroidsLocal()
            Log.d("worker", "asteroids.toString()")
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }


}