package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentRepository

class DeleteCommentUseCase(private val commentRepository: ICommentRepository) {

    suspend fun deleteComment(id: Int): Int {
        return commentRepository.deleteById(id)
    }
}