package hotel.minimal.client.data.repositories.impl

import hotel.minimal.client.data.interfaces.CrudRepository
import hotel.minimal.client.domain.interfaces.ICrudRepository

abstract class CrudRepositoryImpl<E, D>(protected open val repository: CrudRepository<E, D>)
    : BaseRepositoryImpl(), ICrudRepository<E, D> {

    override suspend fun getAll(): List<E> {
        return parseResponse(repository.getAll())
    }

    override suspend fun getById(id: Int): E {
        return parseResponse(repository.getById(id))
    }

    override suspend fun create(dto: D): E {
        return parseResponse(repository.create(dto))
    }

    override suspend fun updateById(id: Int, dto: D): E {
        return parseResponse(repository.updateById(id, dto))
    }

    override suspend fun deleteById(id: Int): Int {
        return parseResponse(repository.deleteById(id)).id
    }
}