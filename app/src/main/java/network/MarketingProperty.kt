package network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketingProperty(
    val title: String,
    val description: String,
    val imageUrl: String,
    val url: String
)