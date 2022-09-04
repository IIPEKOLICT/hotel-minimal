package com.example.dao.services

import com.example.dao.interfaces.ICommentService
import com.example.dtos.CommentDto
import com.example.models.entities.Comment
import com.example.models.entities.Room
import com.example.models.tables.Comments

object CommentService : BaseService<Comment, Comment.Companion>(Comment), ICommentService {

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