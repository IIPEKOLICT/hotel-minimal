package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.Room

class CreateRoomUseCase(private val roomService: IRoomService) {

    suspend fun createRoom(dto: Room) {
        roomService.create(dto)
    }
}