package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment

class UpdateCommentUseCase(private val commentService: ICommentService) {

    suspend fun updateComment(id: Int, content: String) {
        commentService.updateById(id, Comment(content = content))
    }
}