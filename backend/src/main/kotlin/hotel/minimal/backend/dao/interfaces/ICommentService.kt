package hotel.minimal.backend.dao.interfaces

import hotel.minimal.backend.dtos.CommentDto
import hotel.minimal.backend.db.entities.Comment
import hotel.minimal.backend.db.entities.Room

interface ICommentService : IBaseService<Comment> {
    suspend fun getByRoomId(roomId: Int): List<Comment>
    suspend fun create(dto: CommentDto, newRoom: Room): Comment
    suspend fun change(id: Int, dto: CommentDto): Comment
}