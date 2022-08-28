package com.example.models.entities

import com.example.dtos.CommentDto
import com.example.models.interfaces.IEntity
import com.example.models.tables.Comments
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Comment(id: EntityID<Int>) : IntEntity(id), IEntity<CommentDto, CommentDto> {
    val room: Room by Room referencedOn Comments.room

    var content: String by Comments.content

    override fun toDto(): CommentDto {
        return CommentDto(
            id = id.value,
            room = room.id.value,
            content = content
        )
    }

    override fun toPopulatedDto(): CommentDto = toDto()

    companion object : IntEntityClass<Comment>(Comments)
}