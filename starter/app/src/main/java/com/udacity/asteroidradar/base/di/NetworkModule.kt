package com.udacity.asteroidradar.base.di

import android.media.session.MediaSession
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.base.Constants
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidApi
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidService
import com.udacity.asteroidradar.base.data.remote.retrofit.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
    fun provideOkHttpClient(interceptor: TokenInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().apply {
            callTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            if (!interceptors().contains(interceptor)) {
                addInterceptor(interceptor)
            }
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(): TokenInterceptor {
        return TokenInterceptor(BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideAsteroidService(retrofit: Retrofit): AsteroidService {
        return retrofit.create(AsteroidService::class.java)
    }
}