package com.udacity.asteroidradar.base.data.remote.retrofit

import com.udacity.asteroidradar.base.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AsteroidApi {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val asteroid_api: AsteroidService by lazy { retrofit.create(AsteroidService::class.java) }
}