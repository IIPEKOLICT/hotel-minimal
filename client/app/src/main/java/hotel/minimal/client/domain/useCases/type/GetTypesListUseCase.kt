package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class GetTypesListUseCase(private val typeRepository: ITypeRepository) {

    suspend fun getTypesList(): List<Type> {
        return typeRepository.getAll()
    }
}