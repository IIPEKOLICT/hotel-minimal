package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class UpdateCommentUseCase(private val commentRepository: ICommentRepository) {

    suspend fun updateComment(id: Int, content: String): Comment {
        return commentRepository.updateById(id, Comment(content = content))
    }
}