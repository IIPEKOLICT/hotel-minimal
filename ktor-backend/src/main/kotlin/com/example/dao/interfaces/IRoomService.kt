package com.example.dao.interfaces

import com.example.dtos.RoomDto
import com.example.models.entities.Comment
import com.example.models.entities.Room
import com.example.models.entities.Type

interface IRoomService : IBaseService<Room> {
    suspend fun create(dto: RoomDto): Room
    suspend fun change(id: Int, dto: RoomDto): Room
    suspend fun addComment(room: Room, comment: Comment): Room
}