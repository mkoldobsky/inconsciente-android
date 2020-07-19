package com.inconsciente.colectiv.service

import android.content.Context
import com.inconsciente.colectiv.database.Area
import com.inconsciente.colectiv.database.Config
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.network.AreaProperty
import com.inconsciente.colectiv.repository.InconscienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ConfigService constructor(context: Context) {
    val context = context

    fun getAreaFromZipcode(zipcode: String): AreaProperty? {
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)
        val areas = repository.areaList
        return areas.firstOrNull() { area -> area.zipcodes.contains(zipcode) }
    }

    fun updateConfigWithZipcode(zipcode: String) {
        val database = getDatabase(context)
        val repository = InconscienteRepository(database)

        val configToUpdate = repository.getConfig()
        val nextOfferTime = if (configToUpdate == null) Date().time else configToUpdate.nextOfferTime

        val config = Config(zipcode, nextOfferTime)
        repository.saveConfig(config)
    }

}