package hotel.minimal.client.domain.useCases.room

import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.domain.useCases.BaseGetListUseCase
import javax.inject.Inject

class GetRoomsListUseCase @Inject constructor(private val roomService: IRoomService) :
    BaseGetListUseCase<RoomPopulated, Room>(roomService) {

    suspend fun getRoomsList() {
        roomService.getAll()
    }
}