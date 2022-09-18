package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService

class DeleteCommentUseCase(private val commentService: ICommentService) {

    suspend fun deleteComment(id: Int) {
        commentService.deleteById(id)
    }
}