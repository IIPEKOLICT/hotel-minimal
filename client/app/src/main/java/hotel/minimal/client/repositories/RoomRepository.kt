package hotel.minimal.client.repositories

import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.data.dtos.RoomDto
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.models.RoomPopulated
import retrofit2.Response
import retrofit2.http.*

interface RoomRepository {

    @GET("/rooms")
    suspend fun getAll(): Response<List<RoomPopulated>>

    @GET("/rooms/{id}")
    suspend fun getOne(@Path("id") id: Int): Response<RoomPopulated>

    @GET("/rooms/{id}/comments")
    suspend fun getRoomComments(@Path("id") id: Int): Response<List<Comment>>

    @POST("/rooms")
    suspend fun create(@Body dto: RoomDto): Response<RoomPopulated>

    @PUT("/rooms/{id}")
    suspend fun change(
        @Path("id") id: Int,
        @Query("status") status: String,
        @Body dto: RoomDto
    ): Response<RoomPopulated>

    @DELETE("/rooms/{id}")
    suspend fun delete(@Path("id") id: Int): Response<DeleteDto>
}