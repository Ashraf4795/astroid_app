package com.udacity.asteroidradar.base.data.remote.retrofit

import com.udacity.asteroidradar.base.Constants.Asteroid_endpoint
import com.udacity.asteroidradar.base.data.model.Asteroid
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {

    @GET(Asteroid_endpoint)
    suspend fun getAsteroids(
        @Query("start_date") startData: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): List<Asteroid>
}