package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeService
import javax.inject.Inject

class DeleteTypeUseCase @Inject constructor(private val typeService: ITypeService) {

    suspend fun deleteType(id: Int) {
        typeService.deleteById(id)
    }
}