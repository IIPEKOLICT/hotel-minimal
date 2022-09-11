package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.repositories.RoomRepository
import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated

class RoomRepositoryImpl(
    override val repository: RoomRepository
) : CrudRepositoryImpl<RoomPopulated, Room>(repository), IRoomRepository {

    override suspend fun getRoomComments(id: Int): List<Comment> {
        return parseResponse(repository.getRoomComments(id))
    }
}