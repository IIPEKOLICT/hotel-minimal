package com.example.models.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Comments : IntIdTable() {
    val room: Column<EntityID<Int>> = reference("room_id", Rooms)

    val content: Column<String> = varchar("content", 50)
}