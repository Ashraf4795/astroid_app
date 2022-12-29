package com.udacity.asteroidradar.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.base.data.contract.Repository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}