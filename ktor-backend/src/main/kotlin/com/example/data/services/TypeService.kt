package com.example.data.services

import com.example.data.dtos.TypeDto
import com.example.data.interfaces.ITypeService
import com.example.data.models.Type

object TypeService : BaseService<Type, Type.Companion>(Type), ITypeService {
    override suspend fun create(dto: TypeDto): Type {
        return query {
            model.new {
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