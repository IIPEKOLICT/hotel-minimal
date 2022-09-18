package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.RoomPopulated

class GetRoomUseCase(private val roomService: IRoomService) {

    suspend fun getRoom(id: Int): RoomPopulated {
        return roomService.getById(id)
    }
}