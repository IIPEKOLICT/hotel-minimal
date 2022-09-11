package hotel.minimal.client.domain.interfaces

import hotel.minimal.client.domain.models.Comment

interface ICommentRepository {
    suspend fun create(dto: Comment): Comment
    suspend fun updateById(id: Int, dto: Comment): Comment
    suspend fun deleteById(id: Int): Int
}