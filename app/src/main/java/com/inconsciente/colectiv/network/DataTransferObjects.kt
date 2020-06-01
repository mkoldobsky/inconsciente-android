package com.inconsciente.colectiv.network

import com.inconsciente.colectiv.database.MarketingEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketingContainer(val marketings: List<MarketingProperty>)



/**
 * Convert Network results to database objects
 */
fun MarketingContainer.asDomainModel(): List<MarketingProperty> {
    return marketings.map {
        MarketingProperty(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url
           )
    }
}


/**
 * Convert Network results to database objects
 */
fun MarketingContainer.asDatabaseModel(): List<MarketingEntity> {
    return marketings.map {
        MarketingEntity(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url)
    }
}
