package com.inconsciente.colectiv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inconsciente.colectiv.database.Message
import com.inconsciente.colectiv.database.getApplicationDatabase
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.network.MessageProperty

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.inconsciente.colectiv.repository.InconscienteRepository
import java.io.IOException


class MessageViewModel(application: Application): AndroidViewModel(application) {

    var messageSelected :Int = 0

    fun nextMessageSelected(totalMessages: Int){
        if (messageSelected < totalMessages){
            messageSelected++
        }
    }

    fun previousMessageSelected(){
        if (messageSelected > 0){
            messageSelected--
        }
    }
}
