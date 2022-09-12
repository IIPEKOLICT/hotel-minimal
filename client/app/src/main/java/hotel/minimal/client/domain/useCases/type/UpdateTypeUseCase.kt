package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class UpdateTypeUseCase(private val typeRepository: ITypeRepository) {

    suspend fun updateType(id: Int, type: Type): Type {
        return typeRepository.updateById(id, type)
    }
}