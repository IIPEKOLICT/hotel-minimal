package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment
import javax.inject.Inject

class UpdateCommentUseCase @Inject constructor(private val commentService: ICommentService) {

    suspend fun updateComment(id: Int, content: String) {
        commentService.updateById(id, Comment(content = content))
    }
}