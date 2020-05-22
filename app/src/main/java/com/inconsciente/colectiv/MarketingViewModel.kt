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


class MarketingViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    private val _properties = MutableLiveData<List<MarketingProperty>>()

    val properties: LiveData<List<MarketingProperty>>
        get() = _properties


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
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.size} marketing properties retrieved"

                _properties.value = listResult

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
