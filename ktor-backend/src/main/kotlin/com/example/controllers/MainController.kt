package com.example.controllers

import com.example.shared.DatabaseManager
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.mainController() {
    routing {
        get("/") {
            call.respond(mapOf("status" to "ok"))
        }

        get("/lol") {
            call.respond(DatabaseManager.getDatabase().dialect.name)
        }
    }
}