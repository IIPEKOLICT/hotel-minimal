package hotel.minimal.client.domain.useCases.type

import hotel.minimal.client.domain.interfaces.ITypeRepository

class DeleteTypeUseCase(private val typeRepository: ITypeRepository) {

    suspend fun deleteType(id: Int): Int {
        return typeRepository.deleteById(id)
    }
}