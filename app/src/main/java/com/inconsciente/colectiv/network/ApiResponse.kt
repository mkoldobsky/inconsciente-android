package com.inconsciente.colectiv.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val message: String,
    val error: Boolean,
    val code: Int,
    val results: T
)