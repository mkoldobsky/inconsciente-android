package com.inconsciente.colectiv.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreaProperty(
    val name: String
)