package com.inconsciente.colectiv.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inconsciente.colectiv.network.MarketingProperty

@Entity
data class MarketingEntity constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)


fun List<MarketingEntity>.asDomainModel(): List<MarketingProperty> {
    return map {
        MarketingProperty(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url)
    }
}

fun List<MarketingProperty>.asDatabaseModel(): List<MarketingEntity>{
    return map {
        MarketingEntity(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url
        )
    }
}