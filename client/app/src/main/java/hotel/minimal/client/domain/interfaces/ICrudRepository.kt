package hotel.minimal.client.domain.interfaces

interface ICrudRepository<E, D> {
    suspend fun getAll(): List<E>
    suspend fun getById(id: Int): E
    suspend fun create(dto: D): E
    suspend fun updateById(id: Int, dto: D): E
    suspend fun deleteById(id: Int): Int
}