package com.udacity.asteroidradar.base.utils

sealed class Status <T> {

    data class Success<T>(private val data: T?) : Status<T>()
    data class Failure<T>(private val exception: Throwable?, private val errorMessage: String = "error") : Status<T>()
    object Loading : Status<Nothing>()
}