package hotel.minimal.client.data.repositories

import hotel.minimal.client.domain.models.Type
import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.data.interfaces.CrudRepository
import retrofit2.Response
import retrofit2.http.*

interface TypeRepository : CrudRepository<Type, Type> {

    @GET("/types")
    override suspend fun getAll(): Response<List<Type>>

    @GET("/types/{id}")
    override suspend fun getById(@Path("id") id: Int): Response<Type>

    @POST("/types")
    override suspend fun create(@Body dto: Type): Response<Type>

    @PUT("/types/{id}")
    override suspend fun updateById(@Path("id") id: Int, @Body dto: Type): Response<Type>

    @DELETE("/types/{id}")
    override suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}