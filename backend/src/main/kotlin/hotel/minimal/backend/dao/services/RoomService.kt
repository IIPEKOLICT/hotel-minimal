package hotel.minimal.backend.dao.services

import hotel.minimal.backend.dao.interfaces.IRoomService
import hotel.minimal.backend.dtos.RoomDto
import hotel.minimal.backend.db.entities.Room
import hotel.minimal.backend.db.entities.Type

class RoomService : BaseService<Room, Room.Companion>(Room), IRoomService {
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