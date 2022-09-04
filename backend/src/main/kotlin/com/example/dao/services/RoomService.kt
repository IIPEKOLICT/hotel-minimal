package com.example.dao.services

import com.example.dao.interfaces.IRoomService
import com.example.dtos.RoomDto
import com.example.models.entities.Room
import com.example.models.entities.Type

object RoomService : BaseService<Room, Room.Companion>(Room), IRoomService {
    override suspend fun create(dto: RoomDto, newType: Type): Room {
        return query {
            repository.new {
                type = newType
                address = dto.address
                description = dto.description
                floor = dto.floor
                places = dto.places
            }
        }
    }

    override suspend fun change(id: Int, dto: RoomDto, newType: Type): Room {
        return query {
            getById(id).apply {
                type = newType
                address = dto.address
                description = dto.description
                floor = dto.floor
                places = dto.places
                isFree = dto.isFree
            }
        }
    }
}