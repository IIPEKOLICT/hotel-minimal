package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class GetCommentUseCase(private val commentRepository: ICommentRepository) {

    suspend fun getComment(id: Int): Comment {
        return commentRepository.getById(id)
    }
}