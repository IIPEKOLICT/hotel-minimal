package hotel.minimal.client.domain.interfaces

import hotel.minimal.client.domain.models.Comment

interface ICommentService : ICrudService<Comment, Comment> {
    suspend fun getRoomComments(roomId: Int)
}