package com.inconsciente.colectiv.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoResultsApiResponse(
    val message: String,
    val error: Boolean,
    val code: Int
)