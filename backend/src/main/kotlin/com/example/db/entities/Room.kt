package com.example.db.entities

import com.example.dtos.RoomDto
import com.example.dtos.RoomPopulatedDto
import com.example.db.interfaces.IEntity
import com.example.db.tables.Rooms
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

class Room(id: EntityID<Int>) : IntEntity(id), IEntity<RoomDto, RoomPopulatedDto> {
    var type: Type by Type referencedOn Rooms.type

    var description: String by Rooms.description
    var address: String by Rooms.address
    var floor: Int by Rooms.floor
    var places: Int by Rooms.places
    var isFree: Boolean by Rooms.isFree

    override fun toDto(): RoomDto {
        val room: Room = this

        return transaction {
            RoomDto(
                id = room.id.value,
                type = room.type.id.value,
                address = room.address,
                description = room.description,
                floor = room.floor,
                places = room.places,
                isFree = room.isFree
            )
        }
    }

    override fun toPopulatedDto(): RoomPopulatedDto {
        val room: Room = this

        return transaction {
            RoomPopulatedDto(
                id = room.id.value,
                type = room.type.toDto(),
                address = room.address,
                description = room.description,
                floor = room.floor,
                places = room.places,
                isFree = room.isFree
            )
        }
    }

    companion object : IntEntityClass<Room>(Rooms)
}