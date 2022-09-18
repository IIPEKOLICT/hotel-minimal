package hotel.minimal.client.data.repositories

import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated
import retrofit2.Response
import retrofit2.http.*

interface RoomRepository {

    @GET("/rooms")
    suspend fun getAll(): Response<List<RoomPopulated>>

    @GET("/rooms/{id}")
    suspend fun getById(@Path("id") id: Int): Response<RoomPopulated>

    @POST("/rooms")
    suspend fun create(@Body dto: Room): Response<RoomPopulated>

    @PUT("/rooms/{id}")
    suspend fun updateById(@Path("id") id: Int, @Body dto: Room): Response<RoomPopulated>

    @DELETE("/rooms/{id}")
    suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}