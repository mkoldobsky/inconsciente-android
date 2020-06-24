package com.inconsciente.colectiv.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageProperty(
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)