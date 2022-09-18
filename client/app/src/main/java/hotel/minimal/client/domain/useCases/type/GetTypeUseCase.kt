package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeService
import hotel.minimal.client.domain.models.Type

class GetTypeUseCase(private val typeService: ITypeService) {

    suspend fun getType(id: Int): Type {
        return typeService.getById(id)
    }
}