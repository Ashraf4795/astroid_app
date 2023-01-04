package com.udacity.asteroidradar.base.data.remote

import com.udacity.asteroidradar.base.data.contract.RemoteDataSource
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidService
import com.udacity.asteroidradar.base.utils.parseAsteroidsJsonResult
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val asteroidService: AsteroidService) : RemoteDataSource {
    override suspend fun getAsteroids(
        startData: String,
        endDate: String
    ): List<Asteroid> {
        return suspendCancellableCoroutine { continuation ->
            asteroidService.getAsteroids(startData, endDate).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val apiResponseBody = response.body()
                        apiResponseBody?.string()?.let {
                            val asteroids = parseAsteroidsJsonResult(JSONObject(it))
                            continuation.resumeWith(Result.success(asteroids))
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    continuation.resumeWith(Result.failure(t))
                }
            })
        }
    }

    override suspend fun getPictureOfTheDay(): PictureOfDay {
        return asteroidService.getPictureOfTheDay()
    }
}