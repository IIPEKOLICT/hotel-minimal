package loshica.hotel.domain.interfaces

import loshica.hotel.domain.models.Type
import retrofit2.Response

interface ITypeRepository {
    suspend fun getAll(): Response<List<Type>>
}