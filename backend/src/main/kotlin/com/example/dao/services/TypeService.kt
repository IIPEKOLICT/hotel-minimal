package com.example.dao.services

import com.example.dtos.TypeDto
import com.example.dao.interfaces.ITypeService
import com.example.db.entities.Type

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