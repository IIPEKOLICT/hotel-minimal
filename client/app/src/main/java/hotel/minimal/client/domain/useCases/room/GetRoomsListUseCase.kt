package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.RoomPopulated

class GetRoomsListUseCase(private val roomRepository: IRoomRepository) {

    suspend fun getRoomsList(): List<RoomPopulated> {
        return roomRepository.getAll()
    }
}