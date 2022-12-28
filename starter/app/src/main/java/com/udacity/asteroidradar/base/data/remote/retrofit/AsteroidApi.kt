package com.udacity.asteroidradar.base.data.remote.retrofit

import com.udacity.asteroidradar.base.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AsteroidApi {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}