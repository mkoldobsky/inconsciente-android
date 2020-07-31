package com.inconsciente.colectiv.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.inconsciente.colectiv.database.*

import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.MessageProperty

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class InconscienteRepository(private val database: InconscienteDatabase) {

    val messageList = database.messageDao.getMessages().asMessageProperty()

    val areaList = database.areaDao.getAreas().asAreaProperty()

    fun saveConfig(config: Config){
        database.configDao.insertConfig(config)
    }

    fun getConfig():Config{
       return database.configDao.getConfig()
    }

    suspend fun refreshConfig(){
        withContext(Dispatchers.IO){
            val response = InconscienteApi.retrofitService.getConfig()
            Timber.i("inconsciente API called")
            if (response.code == 200){
                val configFromApi = response.results
                val configFromDb = database.configDao.getConfig()
                val zipcode = ""
                if (configFromDb != null){
                val zipcode = configFromDb.zipcode}
                val config = Config(zipcode, configFromApi.nextOfferTime.time)
                database.configDao.insertConfig(config)
                database.messageDao.insertAll(configFromApi.messages.asMessageDatabase())
                database.areaDao.insertAll(configFromApi.areas.asAreaDatabase())
            }
        }
    }
}

