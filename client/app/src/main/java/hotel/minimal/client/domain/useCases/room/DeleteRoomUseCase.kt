package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomRepository

class DeleteRoomUseCase(private val roomRepository: IRoomRepository) {

    suspend fun deleteRoom(id: Int): Int {
        return roomRepository.deleteById(id)
    }
}