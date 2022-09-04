package com.example.models.interfaces

interface IEntity<Dto, PopulatedDto> {
    fun toDto(): Dto
    fun toPopulatedDto(): PopulatedDto
}
