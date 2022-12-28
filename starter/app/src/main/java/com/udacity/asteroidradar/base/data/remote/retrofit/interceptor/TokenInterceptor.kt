package com.udacity.asteroidradar.base.data.remote.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// interceptor for adding api_key query param for all requests to NASA api service.
class TokenInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestUrl = originalRequest.url().apply {
            newBuilder().addQueryParameter("api_key", apiKey).build()
        }

        val requestBuilder = originalRequest.newBuilder().url(requestUrl)
        return chain.proceed(requestBuilder.build())
    }
}