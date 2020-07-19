package com.inconsciente.colectiv.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inconsciente.colectiv.network.AreaProperty
import com.inconsciente.colectiv.network.MessageProperty
import java.sql.Date


@Entity
data class Message constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)
@Entity
data class Area constructor(
    @PrimaryKey
    val name:String,
    val zipcodes: String
)

@Entity
data class Config constructor(
    @PrimaryKey
    val zipcode: String,
    val nextOfferTime: Long
)

fun List<Message>.asMessageProperty(): List<MessageProperty> {
    return map {
        MessageProperty(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url)
    }
}

fun List<MessageProperty>.asMessageDatabase(): List<Message>{
    return map {
        Message(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url
        )
    }
}


fun List<Area>.asAreaProperty(): List<AreaProperty> {
    return map {
        AreaProperty(
            name = it.name,
            zipcodes = it.zipcodes
           )
    }
}

fun List<AreaProperty>.asAreaDatabase(): List<Area>{
    return map {
        Area(
            name = it.name,
            zipcodes = it.zipcodes
        )
    }
}
