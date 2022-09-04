package loshica.hotel.domain.interfaces

import loshica.hotel.domain.models.Room

interface RoomRepository {
    fun getRooms(): List<Room>
}