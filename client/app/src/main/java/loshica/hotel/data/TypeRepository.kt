package loshica.hotel.data

import loshica.hotel.domain.interfaces.ITypeRepository
import loshica.hotel.domain.models.Type
import retrofit2.Response
import retrofit2.http.GET

interface TypeRepository : ITypeRepository {

    @GET("/types")
    override suspend fun getAll(): Response<List<Type>>
}