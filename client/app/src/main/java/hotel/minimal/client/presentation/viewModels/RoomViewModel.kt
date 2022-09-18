package hotel.minimal.client.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hotel.minimal.client.data.DataSource
import kotlinx.coroutines.*
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.domain.DefaultValue
import hotel.minimal.client.domain.useCases.room.*

class RoomViewModel(override val app: Application): BaseViewModel(app) {

    private val currentRoom: MutableLiveData<RoomPopulated> = MutableLiveData(DefaultValue.ROOM)

    private val getRoomsListUseCase = GetRoomsListUseCase(DataSource.roomService)
    private val getRoomUseCase = GetRoomUseCase(DataSource.roomService)
    private val createRoomUseCase = CreateRoomUseCase(DataSource.roomService)
    private val updateRoomUseCase = UpdateRoomUseCase(DataSource.roomService)
    private val deleteRoomUseCase = DeleteRoomUseCase(DataSource.roomService)

    val roomsList: LiveData<List<RoomPopulated>>
        get() = getRoomsListUseCase.liveData

    val room: LiveData<RoomPopulated>
        get() = currentRoom

    private val roomId: Int
        get() = currentRoom.value?.id ?: throw RuntimeException("No roomId")

    init {
        loadRooms()
    }

    private fun loadRooms() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getRoomsListUseCase.getRoomsList()
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun getCurrentRoom(): RoomPopulated = currentRoom.value ?: DefaultValue.ROOM

    fun setCurrentRoom(index: Int?) {
        roomsList.value?.let {
            currentRoom.postValue(if (index == null) DefaultValue.ROOM else it[index])
        }
    }

    fun createRoom(dto: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                createRoomUseCase.createRoom(dto)
                showToast("Room created")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun updateRoom(dto: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateRoomUseCase.updateRoom(roomId, dto)
                currentRoom.postValue(getRoomUseCase.getRoom(roomId))
                showToast("Room updated")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    fun deleteRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteRoomUseCase.deleteRoom(roomId)
                showToast("Room deleted")
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }
}