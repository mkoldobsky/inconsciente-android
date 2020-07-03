package com.inconsciente.colectiv.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Prospect(
    val zipcode: String,
    val name: String,
    val email: String
)