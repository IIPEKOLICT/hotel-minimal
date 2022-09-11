package com.example.db.interfaces

interface IEntity<Dto, PopulatedDto> {
    fun toDto(): Dto
    fun toPopulatedDto(): PopulatedDto
}
