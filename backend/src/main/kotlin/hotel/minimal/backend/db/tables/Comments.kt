package hotel.minimal.backend.db.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object Comments : IntIdTable() {
    val room: Column<EntityID<Int>> = reference("room_id", Rooms, onDelete = ReferenceOption.CASCADE)

    val content: Column<String> = varchar("content", 50)
}