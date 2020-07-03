package com.inconsciente.colectiv.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.inconsciente.colectiv.database.InconscienteDatabase
import com.inconsciente.colectiv.database.asDatabaseModel

import com.inconsciente.colectiv.database.asDomainModel
import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.MessageProperty

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class InconscienteRepository(private val database: InconscienteDatabase) {

    val messageList: LiveData<List<MessageProperty>> = Transformations.map(database.inconscienteDao.getMessages()) {
        it.asDomainModel()
    }
    suspend fun refreshMessage() {
        withContext(Dispatchers.IO) {
            val messageList = InconscienteApi.retrofitService.getMessagePropertiesAsync().await()
            Timber.i("inconsciente API called")
            database.inconscienteDao.insertAll(messageList.asDatabaseModel())
        }
    }
}

