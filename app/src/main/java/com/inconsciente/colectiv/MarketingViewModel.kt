package com.inconsciente.colectiv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.MarketingProperty


enum class MarketingApiStatus { LOADING, ERROR, DONE }

class MarketingViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<MarketingApiStatus>()

    private val _properties = MutableLiveData<List<MarketingProperty>>()

    val status: LiveData<MarketingApiStatus>
        get() = _status

    val properties = _properties


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getMarketingProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarketingProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = InconscienteApi.retrofitService.getPropertiesAsync()
            try {
                _status.value = MarketingApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = MarketingApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = MarketingApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
