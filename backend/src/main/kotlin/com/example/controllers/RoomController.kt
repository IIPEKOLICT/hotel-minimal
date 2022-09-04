package com.example.controllers

import com.example.dao.services.CommentService
import com.example.dao.services.RoomService
import com.example.dao.services.TypeService
import com.example.dtos.DeleteDto
import com.example.dtos.RoomDto
import com.example.errors.ErrorHandler
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.roomController() {
    routing {
        get("/rooms") {
            try {
                call.respond(RoomService.getAll().map { it.toPopulatedDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }

        get("/rooms/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(RoomService.getById(id).toPopulatedDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }

        get("/rooms/{id}/comments") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                RoomService.getById(id)
                call.respond(CommentService.getByRoomId(id).map { it.toDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }

        post("/rooms") {
            try {
                val dto: RoomDto = call.receive()

                val room = RoomService.create(
                    dto = dto,
                    newType = TypeService.getById(dto.type)
                )

                call.respond(room.toPopulatedDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }

        put("/rooms/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: RoomDto = call.receive()

                val room = RoomService.change(
                    id = id,
                    dto = dto,
                    newType = TypeService.getById(dto.type)
                )

                call.respond(room.toPopulatedDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }

        delete("/rooms/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(DeleteDto(id = RoomService.delete(id)))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}