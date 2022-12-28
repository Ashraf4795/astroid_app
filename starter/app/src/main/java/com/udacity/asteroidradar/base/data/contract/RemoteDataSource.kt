package com.udacity.asteroidradar.base.data.contract

import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

interface RemoteDataSource {
    suspend fun getAsteroids(startData: String, endDate: String, apiKey: String): List<Asteroid>
    fun getPictureOfTheDay(apiKey: String): PictureOfDay
}