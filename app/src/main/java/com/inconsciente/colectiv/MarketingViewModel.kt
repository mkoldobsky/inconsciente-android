package com.inconsciente.colectiv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.network.InconscienteApi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.inconsciente.colectiv.network.MarketingProperty
import com.inconsciente.colectiv.repository.InconscienteRepository
import java.io.IOException


enum class MarketingApiStatus { LOADING, ERROR, DONE }

class MarketingViewModel(application: Application): AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<MarketingApiStatus>()

    //private val _properties = MutableLiveData<List<MarketingProperty>>()

    val status: LiveData<MarketingApiStatus>
        get() = _status


    private val inconscienteRepository = InconscienteRepository(getDatabase(application))

    val properties = inconscienteRepository.marketingList

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        refreshDataFromRepository()
    }



    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {

        _status.value = MarketingApiStatus.LOADING
        coroutineScope.launch {
            try {
                inconscienteRepository.refreshMarketing()
                _status.value = MarketingApiStatus.DONE


            } catch (networkError: IOException) {
                _status.value = MarketingApiStatus.ERROR

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
