package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.Moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://inconsciente-api.herokuapp.com"

private val moshi = Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface InconscienteApiClient {
    @GET("marketing")
    fun getProperties():
            Call<List<MarketingProperty>>
}


object InconscienteApi {
    val retrofitService : InconscienteApiClient by lazy {
        retrofit.create(InconscienteApiClient::class.java) }
}
