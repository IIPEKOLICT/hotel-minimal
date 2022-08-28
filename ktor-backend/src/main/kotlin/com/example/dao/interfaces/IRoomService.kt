package com.example.dao.interfaces

import com.example.dtos.TypeDto
import com.example.models.entities.Room
import com.example.models.entities.Type

interface IRoomService : IBaseService<Room> {
    suspend fun create(dto: TypeDto): Type
    suspend fun change(id: Int, dto: TypeDto): Type
}