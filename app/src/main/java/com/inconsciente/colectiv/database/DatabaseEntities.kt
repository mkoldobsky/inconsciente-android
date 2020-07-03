package com.inconsciente.colectiv.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inconsciente.colectiv.network.MessageProperty

@Entity
data class MessageEntity constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)


fun List<MessageEntity>.asDomainModel(): List<MessageProperty> {
    return map {
        MessageProperty(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url)
    }
}

fun List<MessageProperty>.asDatabaseModel(): List<MessageEntity>{
    return map {
        MessageEntity(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            url = it.url
        )
    }
}