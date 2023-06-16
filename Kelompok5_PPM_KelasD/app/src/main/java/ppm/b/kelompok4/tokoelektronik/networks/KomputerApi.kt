package ppm.b.kelompok4.tokoelektronik.networks

import com.skydoves.sandwich.ApiResponse
import ppm.b.kelompok4.tokoelektronik.model.Komputer
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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