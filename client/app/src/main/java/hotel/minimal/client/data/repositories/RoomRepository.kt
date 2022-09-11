package hotel.minimal.client.data.repositories

import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.data.interfaces.CrudRepository
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated
import retrofit2.Response
import retrofit2.http.*

interface RoomRepository : CrudRepository<RoomPopulated, Room> {

    @GET("/rooms")
    override suspend fun getAll(): Response<List<RoomPopulated>>

    @GET("/rooms/{id}")
    override suspend fun getById(@Path("id") id: Int): Response<RoomPopulated>

    @GET("/rooms/{id}/comments")
    suspend fun getRoomComments(@Path("id") id: Int): Response<List<Comment>>

    @POST("/rooms")
    override suspend fun create(@Body dto: Room): Response<RoomPopulated>

    @PUT("/rooms/{id}")
    override suspend fun updateById(@Path("id") id: Int, @Body dto: Room): Response<RoomPopulated>

    @DELETE("/rooms/{id}")
    override suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}