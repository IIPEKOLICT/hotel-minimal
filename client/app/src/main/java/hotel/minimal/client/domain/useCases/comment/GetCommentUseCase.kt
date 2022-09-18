package hotel.minimal.client.domain.useCases.comment

import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment
import javax.inject.Inject

class GetCommentUseCase @Inject constructor(private val commentService: ICommentService) {

    suspend fun getComment(id: Int): Comment {
        return commentService.getById(id)
    }
}