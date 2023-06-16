package ppm.b.kelompok4.tokoelektronik.networks

import com.skydoves.sandwich.ApiResponse
import ppm.b.kelompok4.tokoelektronik.model.Periferal
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PeriferalApi {
    @GET("api/periferal")
    suspend fun all(): ApiResponse<PeriferalGetResponse>

    @GET("api/periferal/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<PeriferalSingleGetResponse>

    @POST("api/periferal")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Periferal): ApiResponse<PeriferalSingleGetResponse>

    @PUT("api/periferal/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Periferal): ApiResponse<PeriferalSingleGetResponse>

    @DELETE("api/periferal/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<PeriferalSingleGetResponse>
}