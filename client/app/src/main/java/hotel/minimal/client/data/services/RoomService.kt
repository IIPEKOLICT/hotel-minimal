package hotel.minimal.client.data.services

import hotel.minimal.client.data.repositories.RoomRepository
import hotel.minimal.client.domain.interfaces.IRoomService
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomService @Inject constructor(
    private val roomRepository: RoomRepository
) : CrudService<RoomPopulated, Room>(), IRoomService {

    override suspend fun getAll() {
        addEntities {
            roomRepository.getAll()
        }
    }

    override suspend fun create(dto: Room) {
        addEntity {
            roomRepository.create(dto)
        }
    }

    override suspend fun updateById(id: Int, dto: Room) {
        updateEntity(id) {
            roomRepository.updateById(it, dto)
        }
    }

    override suspend fun deleteById(id: Int) {
        deleteEntity(id) {
            roomRepository.deleteById(it)
        }
    }
}