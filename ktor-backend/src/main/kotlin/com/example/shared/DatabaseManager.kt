package com.example.shared

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseManager {
    private var database: Database? = null

    private fun getConfiguration(): HikariConfig {
        val configuration: HikariConfig = HikariConfig().apply {
            jdbcUrl = System.getenv("JDBC_DATABASE_URL") ?: "jdbc:postgresql:hotel_minimal?user=postgres&password=password"
            driverClassName = System.getenv("JDBC_DATABASE_DRIVER") ?: "org.postgresql.Driver"
            maximumPoolSize = 10
            isAutoCommit = true
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }

        configuration.validate()
        return configuration
    }

    private val dataSource: HikariDataSource = HikariDataSource(getConfiguration())

    fun connect() {
        database = Database.connect(dataSource)
    }

    fun getDatabase(): Database = database ?: throw RuntimeException("Can't connect to database")
}