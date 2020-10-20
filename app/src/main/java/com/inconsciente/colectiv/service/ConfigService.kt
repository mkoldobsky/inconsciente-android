package com.inconsciente.colectiv.service

import android.content.Context
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.model.Areainterface
import com.inconsciente.colectiv.model.Duration
import com.inconsciente.colectiv.model.Offer
import com.inconsciente.colectiv.network.MessageProperty
import com.inconsciente.colectiv.repository.InconscienteRepository
import java.util.*

class ConfigService constructor(context: Context) {
    val context = context

    suspend fun refreshConfigFromNetwork(){
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)
        repository.refreshConfig()
    }

    fun getAreaFromZipcode(zipcode: String): Areainterface {
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)
        val areas = repository.getAreas()
        return areas.getAreaByZipcode(zipcode)
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

    fun millisecondsToNextOffer(): Long{
        val offer = Offer(
            Duration(Date(System.currentTimeMillis() + (60 * 1000)), Date(System.currentTimeMillis() + (3600 *10000))), "PetitFleur",
        "6 cajas", 3, "https://storage.googleapis.com/inconsciente/petitfleur.jpg")

        return offer.millisecondsToStart()

    }

    fun getNextOffer(): Offer {
        val offer = Offer(
            Duration(Date(System.currentTimeMillis() + (60 * 1000)), Date(System.currentTimeMillis() + (3600 *10000))), "PetitFleur",
            "6 cajas", 3, "https://storage.googleapis.com/inconsciente/petitfleur.jpg")

        return offer

    }

}