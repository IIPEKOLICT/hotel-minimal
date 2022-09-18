package hotel.minimal.client.data.services

import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.domain.interfaces.ICommentService
import hotel.minimal.client.domain.models.Comment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentService @Inject constructor(
    private val commentRepository: CommentRepository
) : CrudService<Comment, Comment>(), ICommentService {

    override suspend fun getAll() {
        addEntities {
            commentRepository.getAll()
        }
    }

    override suspend fun getRoomComments(roomId: Int) {
        addEntities {
            commentRepository.getRoomComments(roomId)
        }
    }

    override suspend fun create(dto: Comment) {
        addEntity {
            commentRepository.create(dto)
        }
    }

    override suspend fun updateById(id: Int, dto: Comment) {
        updateEntity(id) {
            commentRepository.updateById(it, dto)
        }
    }

    override suspend fun deleteById(id: Int) {
        deleteEntity(id) {
            commentRepository.deleteById(it)
        }
    }
}