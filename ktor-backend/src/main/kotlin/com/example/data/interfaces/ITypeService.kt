package com.example.data.interfaces

import com.example.data.dtos.TypeDto
import com.example.data.models.Type

interface ITypeService : IBaseService<Type> {
    suspend fun create(dto: TypeDto): Type
    suspend fun change(id: Int, dto: TypeDto): Type
}