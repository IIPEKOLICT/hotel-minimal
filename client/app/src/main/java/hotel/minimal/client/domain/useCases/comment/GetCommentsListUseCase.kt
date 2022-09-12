package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class GetCommentsListUseCase(private val commentRepository: ICommentRepository) {

    suspend fun getCommentsList(): List<Comment> {
        return commentRepository.getAll()
    }
}