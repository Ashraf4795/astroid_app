package com.udacity.asteroidradar.base.di

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.base.Constants
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidService
import com.udacity.asteroidradar.base.data.remote.retrofit.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: ApiKeyInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().apply {
            if (!interceptors().contains(interceptor)) {
                addInterceptor(interceptor)
            }
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideAsteroidService(retrofit: Retrofit): AsteroidService {
        return retrofit.create(AsteroidService::class.java)
    }
}