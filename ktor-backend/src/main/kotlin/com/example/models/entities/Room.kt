package com.example.models.entities

import com.example.dtos.RoomDto
import com.example.dtos.RoomPopulatedDto
import com.example.models.interfaces.IEntity
import com.example.models.tables.Comments
import com.example.models.tables.Rooms
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class Room(id: EntityID<Int>) : IntEntity(id), IEntity<RoomDto, RoomPopulatedDto> {
    val type: Type by Type referencedOn Rooms.type
    val comments: SizedIterable<Comment> by Comment referrersOn Comments.room

    var description: String by Rooms.description
    var address: String by Rooms.address
    var floor: Int by Rooms.floor
    var places: Int by Rooms.places
    var isFree: Boolean by Rooms.isFree

    override fun toDto(): RoomDto {
        return RoomDto(
            id = id.value,
            type = type.id.value,
            comments = comments.map { it.id.value },
            address = address,
            description = description,
            floor = floor,
            places = places,
            isFree = isFree
        )
    }

    override fun toPopulatedDto(): RoomPopulatedDto {
        return RoomPopulatedDto(
            id = id.value,
            type = type.toDto(),
            comments = comments.map { it.toDto() },
            address = address,
            description = description,
            floor = floor,
            places = places,
            isFree = isFree
        )
    }

    companion object : IntEntityClass<Room>(Rooms)
}