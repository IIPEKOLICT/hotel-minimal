package hotel.minimal.client.data.repositories

import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.data.interfaces.CrudRepository
import hotel.minimal.client.domain.models.Comment
import retrofit2.Response
import retrofit2.http.*

interface CommentRepository : CrudRepository<Comment, Comment> {

    @GET("/comments")
    override suspend fun getAll(): Response<List<Comment>>

    @GET("/comments/{id}")
    override suspend fun getById(@Path("id") id: Int): Response<Comment>

    @POST("/comments")
    override suspend fun create(@Body dto: Comment): Response<Comment>

    @PUT("/comments/{id}")
    override suspend fun updateById(@Path("id") id: Int, @Body dto: Comment): Response<Comment>

    @DELETE("/comments/{id}")
    override suspend fun deleteById(@Path("id") id: Int): Response<DeleteDto>
}