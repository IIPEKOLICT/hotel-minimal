package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.RoomPopulated
import javax.inject.Inject

class GetRoomUseCase @Inject constructor(private val roomService: IRoomService) {

    suspend fun getRoom(id: Int): RoomPopulated {
        return roomService.getById(id)
    }
}