package hotel.minimal.client.data.interfaces

import hotel.minimal.client.data.dtos.DeleteDto
import retrofit2.Response

interface CrudRepository<E, D> {
    suspend fun getAll(): Response<List<E>>
    suspend fun getById(id: Int): Response<E>
    suspend fun create(dto: D): Response<E>
    suspend fun updateById(id: Int, dto: D): Response<E>
    suspend fun deleteById(id: Int): Response<DeleteDto>
}