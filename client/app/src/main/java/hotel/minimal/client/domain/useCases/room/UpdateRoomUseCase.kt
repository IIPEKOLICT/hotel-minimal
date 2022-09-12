package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated

class UpdateRoomUseCase(private val roomRepository: IRoomRepository) {

    suspend fun updateRoom(id: Int, dto: Room): RoomPopulated {
        return roomRepository.updateById(id, dto)
    }
}