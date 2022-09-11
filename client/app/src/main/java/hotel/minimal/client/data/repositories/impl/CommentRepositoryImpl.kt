package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.repositories.CommentRepository
import hotel.minimal.client.domain.interfaces.ICommentRepository
import hotel.minimal.client.domain.models.Comment

class CommentRepositoryImpl(
    private val repository: CommentRepository
) : BaseRepositoryImpl(), ICommentRepository {

    override suspend fun create(dto: Comment): Comment {
        return parseResponse(repository.create(dto))
    }

    override suspend fun updateById(id: Int, dto: Comment): Comment {
        return parseResponse(repository.updateById(id, dto))
    }

    override suspend fun deleteById(id: Int): Int {
        return parseResponse(repository.deleteById(id)).id
    }
}