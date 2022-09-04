package loshica.hotel.domain.useCases.types

import loshica.hotel.domain.interfaces.ITypeRepository
import loshica.hotel.domain.models.Type

class GetTypesUseCase(private val repository: ITypeRepository) {

    fun getTypes(): List<Type> {
        return repository.getAll()
    }
}