package com.example.data.models

import com.example.data.tables.Comments
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Comment(id: EntityID<Int>) : IntEntity(id) {
    val room: Room by Room referencedOn Comments.room

    var content: String by Comments.content

    companion object : IntEntityClass<Comment>(Comments)
}