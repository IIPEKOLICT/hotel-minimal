package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class CreateTypeUseCase(private val typeRepository: ITypeRepository) {

    suspend fun createType(type: Type): Type {
        return typeRepository.create(type)
    }
}