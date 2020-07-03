package com.inconsciente.colectiv.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inconsciente.colectiv.network.MessageProperty

@Entity
data class Message constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)

@Entity
data class Config constructor(
    @PrimaryKey
    val zipcode: String,
    val area: String
)

fun List<Message>.asDomainModel(): List<MessageProperty> {
    return map {
        MessageProperty(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url)
    }
}

fun List<MessageProperty>.asDatabaseModel(): List<Message>{
    return map {
        Message(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url
        )
    }
}

