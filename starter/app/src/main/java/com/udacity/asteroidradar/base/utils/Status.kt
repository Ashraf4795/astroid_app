package com.udacity.asteroidradar.base.utils

sealed class Status {

    data class Success<T>(private val data: T?) : Status()
    data class Failure(private val exception: Throwable?, private val errorMessage: String = "error") : Status()
    object Loading : Status()
}