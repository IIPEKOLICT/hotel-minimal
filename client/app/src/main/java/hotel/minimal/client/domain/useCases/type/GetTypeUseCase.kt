package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeService
import hotel.minimal.client.domain.models.Type
import javax.inject.Inject

class GetTypeUseCase @Inject constructor(private val typeService: ITypeService) {

    suspend fun getType(id: Int): Type {
        return typeService.getById(id)
    }
}