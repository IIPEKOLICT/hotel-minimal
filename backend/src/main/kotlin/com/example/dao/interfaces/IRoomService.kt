package com.example.dao.interfaces

import com.example.dtos.RoomDto
import com.example.db.entities.Room
import com.example.db.entities.Type

interface IRoomService : IBaseService<Room> {
    suspend fun create(dto: RoomDto, newType: Type): Room
    suspend fun change(id: Int, dto: RoomDto, newType: Type): Room
}