package com.example.controllers

import com.example.dtos.TypeDto
import com.example.dao.services.TypeService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.typeController() {
    routing {
        get("/types") {
            try {
                call.respond(TypeService.getAll().map { it.toDto() })
            } catch (e: Exception) {
                call.respond(e.message ?: "Something went wrong")
            }
        }

        get("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(TypeService.getById(id).toDto())
            } catch (e: Exception) {
                call.respond(e.message ?: "Something went wrong")
            }
        }

        post("/types") {
            try {
                val dto: TypeDto = call.receive()
                call.respond(TypeService.create(dto).toDto())
            } catch (e: Exception) {
                call.respond(e.message ?: "Something went wrong")
            }
        }

        put("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: TypeDto = call.receive()
                call.respond(TypeService.change(id, dto).toDto())
            } catch (e: Exception) {
                call.respond(e.message ?: "Something went wrong")
            }
        }

        delete("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                TypeService.delete(id)
                call.respond(mapOf("status" to "ok"))
            } catch (e: Exception) {
                call.respond(e.message ?: "Something went wrong")
            }
        }
    }
}