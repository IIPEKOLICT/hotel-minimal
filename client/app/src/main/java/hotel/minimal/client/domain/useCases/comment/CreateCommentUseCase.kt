package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(private val commentService: ICommentService) {

    suspend fun createComment(roomId: Int, content: String) {
        commentService.create(
            Comment(room = roomId, content = content)
        )
    }
}