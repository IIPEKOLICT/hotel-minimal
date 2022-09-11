package hotel.minimal.client.data.repositories

import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.domain.models.Comment
import retrofit2.Response
import retrofit2.http.*

interface CommentRepository {

    @POST("/comments")
    suspend fun create(@Body dto: Comment): Response<Comment>

    @PUT("/comments/{id}")
    suspend fun updateById(@Path("id") id: Int, @Body dto: Comment): Response<Comment>

    @DELETE("/comments/{id}")
    suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}