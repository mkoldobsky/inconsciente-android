package com.inconsciente.colectiv.service

import android.content.Context
import com.inconsciente.colectiv.database.Config
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.network.AreaProperty
import com.inconsciente.colectiv.network.MessageProperty
import com.inconsciente.colectiv.repository.InconscienteRepository
import timber.log.Timber
import java.util.*

class ConfigService constructor(context: Context) {
    val context = context

    suspend fun refreshConfigFromNetwork(){
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)
        repository.refreshConfig()
    }

    fun getAreaFromZipcode(zipcode: String): AreaProperty? {
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)
        val areas = repository.getAreas()
        return areas.firstOrNull() { area -> area.zipcodes.contains(zipcode) }
    }

    suspend fun updateConfigWithZipcode(zipcode: String) {
        val repository = InconscienteRepository(getDatabase(context))
        repository.updateConfigWithZipcode(zipcode)
    }

    fun getMessages():List<MessageProperty>{
        val repository = InconscienteRepository(getDatabase(context))
        return  repository.getMessages()
    }

    suspend fun updateConfigWithNoShowMessage(noShowMessage: Boolean){
        val repository = InconscienteRepository(getDatabase(context))
        repository.updateConfigWithNoShowMessage(noShowMessage)

    }

    fun getNoShowMessages(): Boolean{
        val repository = InconscienteRepository(getDatabase(context))
        return repository.getNoShowMessages()
    }

    fun geTimeToNextOffer(): Long{
        val repository = InconscienteRepository(getDatabase(context))
        val time = repository.getNextTimeOffer()
        val today = Date()
        Timber.d("NextOfferTime : ${time}")
        return today.getTime() - time
       /* val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24*/

    }

}