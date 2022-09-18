package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeService
import hotel.minimal.client.domain.models.Type

class UpdateTypeUseCase(private val typeService: ITypeService) {

    suspend fun updateType(id: Int, type: Type) {
        typeService.updateById(id, type)
    }
}