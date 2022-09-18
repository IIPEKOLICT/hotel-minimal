package hotel.minimal.client.data.services

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hotel.minimal.client.data.dtos.DeleteDto
import hotel.minimal.client.data.interfaces.IEntity
import hotel.minimal.client.domain.interfaces.ICrudService
import retrofit2.Response

abstract class CrudService<E: IEntity, D> : BaseService(), ICrudService<E, D> {

    private var entities: MutableList<E> = mutableListOf()
    private val entitiesLD: MutableLiveData<List<E>> = MutableLiveData()

    override val liveData: LiveData<List<E>>
        get() = entitiesLD

    private fun updateList() {
        entitiesLD.postValue(entities.toList())
    }

    protected suspend fun addEntities(callback: suspend () -> Response<List<E>>) {
        entities = parseResponse(callback()).toMutableList()
        updateList()
    }

    override suspend fun getById(id: Int): E {
        return entities.find { it.id == id } ?: throw RuntimeException("Not found")
    }

    protected suspend fun addEntity(callback: suspend () -> Response<E>) {
        entities.add(parseResponse(callback()))
        updateList()
    }

    protected suspend fun updateEntity(id: Int, callback: suspend (id: Int) -> Response<E>) {
        val updatedEntity: E = parseResponse(callback(id))
        entities = entities.map { if (it.id == id) updatedEntity else it }.toMutableList()
        updateList()
    }

    protected suspend fun deleteEntity(id: Int, callback: suspend (id: Int) -> Response<DeleteDto>) {
        val deletedId: Int = parseResponse(callback(id)).id

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            entities.removeIf { it.id == deletedId }
        } else {
            entities = entities.filter { it.id != deletedId }.toMutableList()
        }

        updateList()
    }
}