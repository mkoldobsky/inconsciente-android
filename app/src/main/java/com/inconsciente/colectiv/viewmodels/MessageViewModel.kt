package com.inconsciente.colectiv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inconsciente.colectiv.database.getDatabase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.inconsciente.colectiv.repository.InconscienteRepository
import java.io.IOException


enum class MessageApiStatus { LOADING, ERROR, DONE }

class MessageViewModel(application: Application): AndroidViewModel(application) {

    private val _status = MutableLiveData<MessageApiStatus>()

    val status: LiveData<MessageApiStatus>
        get() = _status

    private val inconscienteRepository = InconscienteRepository(getDatabase(application))

    val properties = inconscienteRepository.messageList

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

        _status.value =
            MessageApiStatus.LOADING
        coroutineScope.launch {
            try {
                inconscienteRepository.refreshMessage()
                _status.value =
                    MessageApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value =
                    MessageApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
