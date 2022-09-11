package com.example.db.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object Rooms : IntIdTable() {
    val type: Column<EntityID<Int>> = reference("type_id", Types, onDelete = ReferenceOption.CASCADE)

    val description: Column<String> = varchar("description", 50).default("")
    val address: Column<String> = varchar("address", 50).default("")
    val floor: Column<Int> = integer("floor").default(0)
    val places: Column<Int> = integer("places").default(0)
    val isFree: Column<Boolean> = bool("isFree").default(true)
}