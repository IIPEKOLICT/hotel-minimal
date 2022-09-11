package hotel.minimal.client.domain.useCases.types

import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class GetTypesUseCase(private val repository: ITypeRepository) {

    fun getTypes(): List<Type> {
        return repository.getAll()
    }
}