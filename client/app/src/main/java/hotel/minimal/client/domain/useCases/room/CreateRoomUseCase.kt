package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated

class CreateRoomUseCase(private val roomRepository: IRoomRepository) {

    suspend fun createRoom(dto: Room): RoomPopulated {
        return roomRepository.create(dto)
    }
}