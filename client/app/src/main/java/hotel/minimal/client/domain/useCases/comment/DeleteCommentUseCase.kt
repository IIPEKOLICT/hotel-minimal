package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import javax.inject.Inject

class DeleteCommentUseCase @Inject constructor(private val commentService: ICommentService) {

    suspend fun deleteComment(id: Int) {
        commentService.deleteById(id)
    }
}