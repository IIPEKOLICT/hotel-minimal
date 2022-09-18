package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.Room

class UpdateRoomUseCase(private val roomService: IRoomService) {

    suspend fun updateRoom(id: Int, dto: Room) {
        roomService.updateById(id, dto)
    }
}