package com.udacity.asteroidradar.base.data.remote.retrofit

import com.udacity.asteroidradar.base.Constants
import com.udacity.asteroidradar.base.data.remote.retrofit.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AsteroidApi {

    @Volatile
    private lateinit var asteroid_api: AsteroidService

    private fun okHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(apiKey))
            .build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getAsteroidService(apiKey: String): AsteroidService {
        if (this::asteroid_api.isInitialized) return this.asteroid_api

        synchronized(this) {
            asteroid_api = retrofit(okHttpClient(apiKey)).create(AsteroidService::class.java)
            return asteroid_api
        }
    }
}