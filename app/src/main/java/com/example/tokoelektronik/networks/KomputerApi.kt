package com.example.tokoelektronik.networks

import com.example.tokoelektronik.model.Komputer
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface KomputerApi {
    @GET("api/komputer")
    suspend fun all(): ApiResponse<KomputerGetResponse>

    @GET("api/komputer/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<KomputerSingleGetResponse>

    @POST("api/komputer")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Komputer): ApiResponse<KomputerSingleGetResponse>

    @PUT("api/komputer/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Komputer): ApiResponse<KomputerSingleGetResponse>

    @DELETE("api/komputer/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<KomputerSingleGetResponse>
}