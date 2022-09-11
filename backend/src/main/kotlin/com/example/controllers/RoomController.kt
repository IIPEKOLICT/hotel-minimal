package com.example.controllers

import com.example.dtos.DeleteDto
import com.example.dtos.RoomDto
import com.example.errors.ErrorHandler
import io.github.smiley4.ktorswaggerui.dsl.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

class RoomController(application: Application) : BaseController(application, "rooms") {
    override val handlers: Set<(route: Route) -> Unit>
        get() = setOf(
            { getAll(it) },
            { getById(it) },
            { getCommentsByRoom(it) },
            { create(it) },
            { updateById(it) },
            { deleteById(it) }
        )

    override val swaggerCallback: OpenApiRoute.() -> Unit
        get() = {
            tags = listOf("rooms")
            description = "Room controller"
        }

    private fun getAll(route: Route) {
        route.get({ description = "get all rooms" }) {
            try {
                call.respond(roomService.getAll().map { it.toPopulatedDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun getById(route: Route) {
        route.get("{id}", { description = "get room by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(roomService.getById(id).toPopulatedDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun getCommentsByRoom(route: Route) {
        route.get("{id}/comments", { description = "get comments by room" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                roomService.getById(id)
                call.respond(commentService.getByRoomId(id).map { it.toDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun create(route: Route) {
        route.post({ description = "create room" }) {
            try {
                val dto: RoomDto = call.receive()

                val room = roomService.create(
                    dto = dto,
                    newType = typeService.getById(dto.type)
                )

                call.respond(room.toPopulatedDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }
    }

    private fun updateById(route: Route) {
        route.put("{id}", { description = "update room by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: RoomDto = call.receive()

                val room = roomService.change(
                    id = id,
                    dto = dto,
                    newType = typeService.getById(dto.type)
                )

                call.respond(room.toPopulatedDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }
    }

    private fun deleteById(route: Route) {
        route.delete("{id}", { description = "delete room by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(DeleteDto(id = roomService.delete(id)))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}