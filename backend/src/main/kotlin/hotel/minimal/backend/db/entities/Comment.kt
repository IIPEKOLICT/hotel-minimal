package hotel.minimal.backend.db.entities

import hotel.minimal.backend.dtos.CommentDto
import hotel.minimal.backend.db.interfaces.IEntity
import hotel.minimal.backend.db.tables.Comments
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

class Comment(id: EntityID<Int>) : IntEntity(id), IEntity<CommentDto, CommentDto> {
    var room: Room by Room referencedOn Comments.room

    var content: String by Comments.content

    override fun toDto(): CommentDto {
        val comment: Comment = this

        return transaction {
            CommentDto(
                id = comment.id.value,
                room = comment.room.id.value,
                content = comment.content
            )
        }
    }

    override fun toPopulatedDto(): CommentDto = toDto()

    companion object : IntEntityClass<Comment>(Comments)
}