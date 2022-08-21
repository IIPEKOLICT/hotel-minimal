package com.example.models.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Types : IntIdTable() {
    val name: Column<String> = varchar("name", 50).uniqueIndex()
    val options: Column<String> = varchar("options", 50).default("no options")
    val price: Column<Int> = integer("price").default(0)
}