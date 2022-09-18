package hotel.minimal.client.data.services

import hotel.minimal.client.data.repositories.TypeRepository
import hotel.minimal.client.domain.interfaces.ITypeService
import hotel.minimal.client.domain.models.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypeService @Inject constructor(
    private val typeRepository: TypeRepository
) : CrudService<Type, Type>(), ITypeService {

    override suspend fun getAll() {
        addEntities {
            typeRepository.getAll()
        }
    }

    override suspend fun create(dto: Type) {
        addEntity {
            typeRepository.create(dto)
        }
    }

    override suspend fun updateById(id: Int, dto: Type) {
        updateEntity(id) {
            typeRepository.updateById(it, dto)
        }
    }

    override suspend fun deleteById(id: Int) {
        deleteEntity(id) {
            typeRepository.deleteById(it)
        }
    }
}