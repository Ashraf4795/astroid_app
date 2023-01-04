package com.udacity.asteroidradar.feature.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity
import com.udacity.asteroidradar.base.data.model.PictureOfDay
import com.udacity.asteroidradar.base.utils.Status
import com.udacity.asteroidradar.base.utils.getNextSevenDaysFormattedDates
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val repository: Repository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val TAG = "MainViewModel"
    private val nextWeekDate = getNextSevenDaysFormattedDates()

    private val _asteroidsStatus = MutableLiveData<Status>()
    val asteroidsStatus: LiveData<Status> = _asteroidsStatus

    private val _pictureOfDay = MutableLiveData<PictureOfDayEntity?>()
    val pictureOfDay = _pictureOfDay

    private val asteroidExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _asteroidsStatus.postValue(Status.Failure(throwable))
    }

    private val mainViewModelCoroutineContext = asteroidExceptionHandler + dispatcher

    init {
        getAsteroids()
        getPictureOfDay()
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            try {
                _asteroidsStatus.value = Status.Loading
                val asteroidsResponse = withContext(mainViewModelCoroutineContext) {
                    repository.getAsteroids(
                        nextWeekDate.first(),
                        nextWeekDate.last()
                    )
                }
                _asteroidsStatus.value = Status.Success(asteroidsResponse)

            } catch (exception: Exception) {
                _asteroidsStatus.value = Status.Failure(exception)
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
                val pictureOfDay = withContext(mainViewModelCoroutineContext) {
                    repository.getPictureOfDay()
                }
                _pictureOfDay.value = pictureOfDay
            } catch (exception: Exception) {
                Log.d(TAG, exception.message.toString())
                Log.d(TAG, pictureOfDay.value.toString())
            }
        }
    }

    private fun launch(
        context: CoroutineContext = mainViewModelCoroutineContext,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(context) {
            block.invoke()
        }
    }
}