package ppm.b.kelompok4.tokoelektronik.networks

import com.skydoves.sandwich.ApiResponse
import ppm.b.kelompok4.tokoelektronik.model.Smartphone
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface SmartphoneApi {
    @GET("api/smartphone")
    suspend fun all(): ApiResponse<SmartphoneGetResponse>

    @GET("api/smartphone/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<SmartphoneSingleGetResponse>

    @POST("api/smartphone")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Smartphone): ApiResponse<SmartphoneSingleGetResponse>

    @PUT("api/smartphone/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Smartphone): ApiResponse<SmartphoneSingleGetResponse>

    @DELETE("api/smartphone/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<SmartphoneSingleGetResponse>
}