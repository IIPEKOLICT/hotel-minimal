package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment
import hotel.minimal.client.domain.useCases.BaseGetListUseCase

class GetRoomCommentsListUseCase(private val commentService: ICommentService) :
    BaseGetListUseCase<Comment, Comment>(commentService) {

    suspend fun getRoomCommentsList(roomId: Int) {
        return commentService.getRoomComments(roomId)
    }
}