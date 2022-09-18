package hotel.minimal.client.data.repositories

import hotel.minimal.client.domain.models.Type
import hotel.minimal.client.data.dtos.DeleteDto
import retrofit2.Response
import retrofit2.http.*

interface TypeRepository {

    @GET("/types")
    suspend fun getAll(): Response<List<Type>>

    @GET("/types/{id}")
    suspend fun getById(@Path("id") id: Int): Response<Type>

    @POST("/types")
    suspend fun create(@Body dto: Type): Response<Type>

    @PUT("/types/{id}")
    suspend fun updateById(@Path("id") id: Int, @Body dto: Type): Response<Type>

    @DELETE("/types/{id}")
    suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}