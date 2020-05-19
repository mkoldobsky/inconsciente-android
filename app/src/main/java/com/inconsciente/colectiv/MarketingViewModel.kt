package com.inconsciente.colectiv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import network.InconscienteApi
import network.MarketingProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketingViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        InconscienteApi.retrofitService.getProperties().enqueue(
            object: Callback<List<MarketingProperty>> {
                override fun onFailure(call: Call<List<MarketingProperty>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<List<MarketingProperty>>, response: Response<List<MarketingProperty>>) {
                    _response.value =
                        "Success: ${response.body()?.size} Mars properties retrieved"
                }
            })
    }
}
