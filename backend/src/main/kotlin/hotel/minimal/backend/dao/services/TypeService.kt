package hotel.minimal.backend.dao.services

import hotel.minimal.backend.dtos.TypeDto
import hotel.minimal.backend.dao.interfaces.ITypeService
import hotel.minimal.backend.db.entities.Type

class TypeService : BaseService<Type, Type.Companion>(Type), ITypeService {
    override suspend fun create(dto: TypeDto): Type {
        return query {
            repository.new {
                name = dto.name
                options = dto.options
                price = dto.price
            }
        }
    }

    override suspend fun change(id: Int, dto: TypeDto): Type {
        return query {
            this.getById(id).apply {
                name = dto.name
                options = dto.options
                price = dto.price
            }
        }
    }
}