package com.example.controllers

import com.example.dtos.TypeDto
import com.example.dao.services.TypeService
import com.example.errors.ErrorHandler
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
                return@get ErrorHandler.handle(call, e)
            }
        }

        get("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(TypeService.getById(id).toDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }

        post("/types") {
            try {
                val dto: TypeDto = call.receive()
                call.respond(TypeService.create(dto).toDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }

        put("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: TypeDto = call.receive()
                call.respond(TypeService.change(id, dto).toDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }

        delete("/types/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                TypeService.delete(id)
                call.respond(mapOf("status" to "ok"))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}