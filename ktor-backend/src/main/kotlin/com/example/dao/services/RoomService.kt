package com.example.dao.services

import com.example.dao.interfaces.IRoomService
import com.example.dtos.RoomDto
import com.example.models.entities.Comment
import com.example.models.entities.Room
import org.jetbrains.exposed.sql.SizedCollection

object RoomService : BaseService<Room, Room.Companion>(Room), IRoomService {

    override suspend fun create(dto: RoomDto): Room {
        return query {
            repository.new {
                address = dto.address
                description = dto.description
                floor = dto.floor
                places = dto.places
                isFree = dto.isFree
            }
        }
    }

    override suspend fun change(id: Int, dto: RoomDto): Room {
        return query {
            getById(id).apply {
                address = dto.address
                description = dto.description
                floor = dto.floor
                places = dto.places
                isFree = dto.isFree
            }
        }
    }

    override suspend fun addComment(room: Room, comment: Comment): Room {
        return query {
            room.apply {
                TODO("NOT YET IMPLEMENTED")
            }
        }
    }
}