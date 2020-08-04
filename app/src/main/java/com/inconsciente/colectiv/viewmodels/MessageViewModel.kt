package com.inconsciente.colectiv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel


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
