package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.IRoomRepository
import hotel.minimal.client.domain.models.Comment

class GetRoomCommentsListUseCase(private val roomRepository: IRoomRepository) {

    suspend fun getRoomCommentsList(roomId: Int): List<Comment> {
        return roomRepository.getRoomComments(roomId)
    }
}