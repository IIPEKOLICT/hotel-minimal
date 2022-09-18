package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import javax.inject.Inject

class DeleteRoomUseCase @Inject constructor(private val roomService: IRoomService) {

    suspend fun deleteRoom(id: Int) {
        roomService.deleteById(id)
    }
}