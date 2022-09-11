package hotel.minimal.client.domain.interfaces

import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated

interface IRoomRepository : ICrudRepository<RoomPopulated, Room> {
    suspend fun getRoomComments(id: Int): List<Comment>
}