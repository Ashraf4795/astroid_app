package com.udacity.asteroidradar.base.utils

import com.udacity.asteroidradar.base.data.model.Asteroid

sealed class Status {

    data class Success(val data: List<Asteroid>?) : Status()
    data class Failure(val exception: Throwable?, val errorMessage: String = "error") : Status()
    object Loading : Status()
}