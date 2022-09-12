package hotel.minimal.backend.dao.services

import hotel.minimal.backend.dao.interfaces.ICommentService
import hotel.minimal.backend.dtos.CommentDto
import hotel.minimal.backend.db.entities.Comment
import hotel.minimal.backend.db.entities.Room
import hotel.minimal.backend.db.tables.Comments

class CommentService : BaseService<Comment, Comment.Companion>(Comment), ICommentService {

    override suspend fun getByRoomId(roomId: Int): List<Comment> {
        return query {
            repository.find { Comments.room eq roomId }.toList()
        }
    }

    override suspend fun create(dto: CommentDto, newRoom: Room): Comment {
        return query {
            repository.new {
                room = newRoom
                content = dto.content
            }
        }
    }

    override suspend fun change(id: Int, dto: CommentDto): Comment {
        return query {
            getById(id).apply {
                content = dto.content
            }
        }
    }
}