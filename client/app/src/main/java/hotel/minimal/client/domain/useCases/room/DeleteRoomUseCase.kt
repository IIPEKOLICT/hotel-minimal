package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService

class DeleteRoomUseCase(private val roomService: IRoomService) {

    suspend fun deleteRoom(id: Int) {
        roomService.deleteById(id)
    }
}