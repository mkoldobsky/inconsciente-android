package com.inconsciente.colectiv.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.inconsciente.colectiv.database.InconscienteDatabase
import com.inconsciente.colectiv.database.MarketingEntity
import com.inconsciente.colectiv.database.asDomainModel
import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.MarketingProperty
import com.inconsciente.colectiv.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class InconscienteRepository(private val database: InconscienteDatabase) {

    val marketingList: LiveData<List<MarketingProperty>> = Transformations.map(database.inconscienteDao.getMarketings()) {
        it.asDomainModel()
    }
    suspend fun refreshMarketing() {
        withContext(Dispatchers.IO) {
            val marketingList = InconscienteApi.retrofitService.getMarketingPropertiesAsync().await()
            database.inconscienteDao.insertAll(marketingList.asDatabaseModel())
        }
    }
}

