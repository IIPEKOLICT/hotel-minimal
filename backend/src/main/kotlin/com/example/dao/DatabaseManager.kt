package com.example.dao

import com.example.models.tables.Comments
import com.example.models.tables.Rooms
import com.example.models.tables.Types
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {
    private var database: Database? = null

    private fun getDataSource(applicationConfig: ApplicationConfig): HikariDataSource {
        return HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = applicationConfig.property("database.jdbcUrl").getString()
                driverClassName = applicationConfig.property("database.driverClassName").getString()
                maximumPoolSize = applicationConfig.property("database.maximumPoolSize").getString().toInt()
                isAutoCommit = applicationConfig.property("database.isAutoCommit").getString() == "true"
                transactionIsolation = applicationConfig.property("database.transactionIsolation").getString()

                validate()
            }
        )
    }

    fun init(applicationConfig: ApplicationConfig) {
        database = Database.connect(getDataSource(applicationConfig))

        transaction(database) {
            SchemaUtils.create(Comments, Rooms, Types)
        }
    }
}