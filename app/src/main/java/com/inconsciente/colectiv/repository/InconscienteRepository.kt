package com.inconsciente.colectiv.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.inconsciente.colectiv.database.*
import com.inconsciente.colectiv.network.AreaProperty

import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.MessageProperty

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class InconscienteRepository(private val database: InconscienteDatabase) {

    fun getMessages(): List<MessageProperty> {
        return database.messageDao.getMessages().asMessageProperty()
    }


    fun getAreas(): List<AreaProperty> {
        return database.areaDao.getAreas().asAreaProperty()
    }


    fun saveConfig(config: Config) {
        database.configDao.insertConfig(config)
    }

    fun getConfig(): Config {
        return database.configDao.getConfig()
    }

    suspend fun refreshConfig() {
        withContext(Dispatchers.IO) {
            val response = InconscienteApi.retrofitService.getConfig()
            if (response.code == 200) {
                val configFromApi = response.results
                val configFromDb = database.configDao.getConfig()
                var zipcode = "nozipcode"
                var noShowMessage = false
                if (configFromDb != null) {
                    zipcode = configFromDb.zipcode
                    noShowMessage = configFromDb.noShowMessage
                }
                val config = Config(zipcode, noShowMessage, configFromApi.nextOfferTime.time)
                database.configDao.deleteConfig()
                database.configDao.insertConfig(config)
                database.messageDao.insertAll(configFromApi.messages.asMessageDatabase())
                database.areaDao.insertAll(configFromApi.areas.asAreaDatabase())
            }
        }
    }

    suspend fun updateConfigWithNoShowMessage(noShowMessage: Boolean) {
        withContext(Dispatchers.IO) {
            val configFromDb = database.configDao.getConfig()
            var config = Config(configFromDb.zipcode, noShowMessage, configFromDb.nextOfferTime)
            database.configDao.deleteConfig()
            database.configDao.insertConfig(config)
        }

    }

    suspend fun updateConfigWithZipcode(zipcode: String) {
        withContext(Dispatchers.IO) {
            val configFromDb = database.configDao.getConfig()
            var config = Config(zipcode, configFromDb.noShowMessage, configFromDb.nextOfferTime)
            database.configDao.deleteConfig()
            database.configDao.insertConfig(config)
        }

    }

    fun getNoShowMessages():Boolean{
        val configFromDb = database.configDao.getConfig()
        return configFromDb.noShowMessage
    }

    fun getNextTimeOffer():Long{
        val configFromDb = database.configDao.getConfig()
        return configFromDb.nextOfferTime
    }
}

