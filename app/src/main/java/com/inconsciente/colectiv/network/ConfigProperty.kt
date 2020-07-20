package com.inconsciente.colectiv.network


import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ConfigProperty(
    val areas: List<AreaProperty>,
    val messages: List<MessageProperty>,
    val nextOfferTime: Date
)

