package com.example.dao.interfaces

import com.example.dtos.CommentDto
import com.example.models.entities.Comment
import com.example.models.entities.Room

interface ICommentService : IBaseService<Comment> {
    suspend fun getByRoomId(roomId: Int): List<Comment>
    suspend fun create(dto: CommentDto, newRoom: Room): Comment
    suspend fun change(id: Int, dto: CommentDto): Comment
}