package hotel.minimal.backend.dao.interfaces

import hotel.minimal.backend.dtos.RoomDto
import hotel.minimal.backend.db.entities.Room
import hotel.minimal.backend.db.entities.Type

interface IRoomService : IBaseService<Room> {
    suspend fun create(dto: RoomDto, newType: Type): Room
    suspend fun change(id: Int, dto: RoomDto, newType: Type): Room
}