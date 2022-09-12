package hotel.minimal.backend.dao.interfaces

import hotel.minimal.backend.dtos.TypeDto
import hotel.minimal.backend.db.entities.Type

interface ITypeService : IBaseService<Type> {
    suspend fun create(dto: TypeDto): Type
    suspend fun change(id: Int, dto: TypeDto): Type
}