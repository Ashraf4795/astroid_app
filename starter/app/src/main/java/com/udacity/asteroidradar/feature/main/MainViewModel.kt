package com.udacity.asteroidradar.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.utils.Status
import com.udacity.asteroidradar.base.utils.getNextSevenDaysFormattedDates
import kotlinx.coroutines.*

class MainViewModel(
    private val repository: Repository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val nextWeekDate = getNextSevenDaysFormattedDates()

    private val _asteroidsStatus = MutableLiveData<Status<List<Asteroid>>>()
    val asteroidsStatus: LiveData<Status<List<Asteroid>>> = _asteroidsStatus

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _asteroidsStatus.value = Status.Failure(throwable)
    }

    private val mainViewModelCoroutineContext = exceptionHandler + dispatcher

    init {
        viewModelScope.launch(mainViewModelCoroutineContext) {
            val asteroidsResponse = repository.getAsteroidsRemote(
                nextWeekDate.first(),
                nextWeekDate.last()
            )
            withContext(Dispatchers.Main) {
                _asteroidsStatus.value = Status.Success(asteroidsResponse)
                saveAsteroids(asteroidsResponse)
            }
        }
    }

    private fun saveAsteroids(asteroidsResponse: List<Asteroid>) {
        viewModelScope.launch(mainViewModelCoroutineContext) {
            val asteroidsEntities = asteroidsResponse.map { AsteroidEntity.convert(it) }
            repository.addAsteroids(asteroidsEntities)
        }
    }
}