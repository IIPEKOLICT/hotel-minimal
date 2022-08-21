package com.example.models.entities

import com.example.dtos.TypeDto
import com.example.models.tables.Rooms
import com.example.models.tables.Types
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class Type(id: EntityID<Int>) : IntEntity(id) {
    val rooms: SizedIterable<Room> by Room referrersOn Rooms.type

    var name: String by Types.name
    var options: String by Types.options
    var price: Int by Types.price

    fun toDto(): TypeDto = TypeDto(
        id = id.value,
        name = name,
        options = options,
        price = price
    )

    companion object : IntEntityClass<Type>(Types)
}