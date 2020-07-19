package com.inconsciente.colectiv.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://inconsciente.com.ar"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    //.addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface InconscienteApiClient {
    @GET("/message")
    suspend fun getMessagePropertiesAsync(): ApiResponse<List<MessageProperty>>
    @GET("/zipcode/{zipcode}/area")
    suspend fun getAreaByZipcode(@Path("zipcode") zipcode : String) : ApiResponse<AreaProperty>
    @POST("/prospect")
    suspend fun postProspect(@Body prospect: Prospect) : ApiResponse<Prospect>
    @GET("/config/android")
    suspend fun getConfig() : ApiResponse<ConfigProperty>
}


object InconscienteApi {
    val retrofitService : InconscienteApiClient by lazy {
        retrofit.create(InconscienteApiClient::class.java) }
}
