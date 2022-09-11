package com.example.dao.interfaces

import com.example.dtos.TypeDto
import com.example.db.entities.Type

interface ITypeService : IBaseService<Type> {
    suspend fun create(dto: TypeDto): Type
    suspend fun change(id: Int, dto: TypeDto): Type
}