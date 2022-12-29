package com.udacity.asteroidradar.base.data.remote.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// interceptor for adding api_key query param for all requests to NASA api service.
class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val requestUrl = originalUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
        val request = originalRequest.newBuilder().url(requestUrl).build()

        return chain.proceed(request)
    }
}