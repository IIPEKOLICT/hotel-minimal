package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class CreateCommentUseCase(private val commentRepository: ICommentRepository) {

    suspend fun createComment(roomId: Int, content: String): Comment {
        return commentRepository.create(
            Comment(room = roomId, content = content)
        )
    }
}