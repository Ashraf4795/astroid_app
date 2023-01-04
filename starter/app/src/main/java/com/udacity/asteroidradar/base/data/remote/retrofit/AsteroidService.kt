package com.udacity.asteroidradar.base.data.remote.retrofit

import com.udacity.asteroidradar.base.Constants.ASTEROID_END_POINT
import com.udacity.asteroidradar.base.Constants.PICTURE_OF_DAY_END_POINT
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {

    @GET(ASTEROID_END_POINT)
    fun getAsteroids(
        @Query("start_date") startData: String,
        @Query("end_date")   endDate: String
    ): Call<ResponseBody>

    @GET(PICTURE_OF_DAY_END_POINT)
    suspend fun getPictureOfTheDay(): PictureOfDay
}