package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.RoomPopulated

class GetRoomUseCase(private val roomRepository: IRoomRepository) {

    suspend fun getRoom(id: Int): RoomPopulated {
        return roomRepository.getById(id)
    }
}