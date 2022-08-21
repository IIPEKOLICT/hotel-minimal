package com.example.models.entities

import com.example.models.tables.Comments
import com.example.models.tables.Rooms
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class Room(id: EntityID<Int>) : IntEntity(id) {
    val type: Type by Type referencedOn Rooms.type
    val comments: SizedIterable<Comment> by Comment referrersOn Comments.room

    var description: String by Rooms.description
    var address: String by Rooms.address
    var floor: Int by Rooms.floor
    var places: Int by Rooms.places
    var isFree: Boolean by Rooms.isFree

    companion object : IntEntityClass<Room>(Rooms)
}