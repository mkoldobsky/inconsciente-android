package com.inconsciente.colectiv.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import java.util.*

@JsonClass(generateAdapter = true)
data class ConfigProperty(
    val areas: List<AreaProperty>,
    val messages: List<MessageProperty>,
    val nextOfferTime: Date
)

class ListAdapter {

    @FromJson
    fun fromJson(reader: JsonReader, jsonAdapter: JsonAdapter<YourResponse>): List<YourResponse>? {
        val list = ArrayList<YourLResponse>()
        if (reader.hasNext()) {
            val token = reader.peek()
            if (token == JsonReader.Token.BEGIN_ARRAY) {
                reader.beginArray()
                while (reader.hasNext()) {
                    val yourResponse = jsonAdapter.fromJsonValue(reader.readJsonValue())
                    YoutResponse?.let {
                        list.add(yourResponse)
                    }
                }
                reader.endArray()
            }
        }
        return list.toList()
    }}