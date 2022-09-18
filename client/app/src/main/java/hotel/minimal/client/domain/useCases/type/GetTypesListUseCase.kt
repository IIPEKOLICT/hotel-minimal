package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeService
import hotel.minimal.client.domain.models.Type
import hotel.minimal.client.domain.useCases.BaseGetListUseCase
import javax.inject.Inject

class GetTypesListUseCase @Inject constructor(private val typeService: ITypeService) :
    BaseGetListUseCase<Type, Type>(typeService) {

    suspend fun getTypesList() {
        typeService.getAll()
    }
}