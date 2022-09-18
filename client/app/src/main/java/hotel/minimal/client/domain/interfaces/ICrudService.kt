package hotel.minimal.client.domain.interfaces

import androidx.lifecycle.LiveData

interface ICrudService<E, D> {
    val liveData: LiveData<List<E>>

    suspend fun getAll()
    suspend fun getById(id: Int): E
    suspend fun create(dto: D)
    suspend fun updateById(id: Int, dto: D)
    suspend fun deleteById(id: Int)
}