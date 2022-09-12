package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeRepository
import hotel.minimal.client.domain.models.Type

class GetTypeUseCase(private val typeRepository: ITypeRepository) {

    suspend fun getType(id: Int): Type {
        return typeRepository.getById(id)
    }
}