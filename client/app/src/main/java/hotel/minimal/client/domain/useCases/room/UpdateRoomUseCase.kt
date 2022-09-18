package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.Room
import javax.inject.Inject

class UpdateRoomUseCase @Inject constructor(private val roomService: IRoomService) {

    suspend fun updateRoom(id: Int, dto: Room) {
        roomService.updateById(id, dto)
    }
}