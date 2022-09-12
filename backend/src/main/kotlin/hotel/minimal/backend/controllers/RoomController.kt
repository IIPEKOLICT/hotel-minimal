package hotel.minimal.backend.controllers

import hotel.minimal.backend.core.annotations.Controller
import hotel.minimal.backend.core.annotations.Handler
import hotel.minimal.backend.dtos.DeleteDto
import hotel.minimal.backend.dtos.RoomDto
import hotel.minimal.backend.errors.ErrorHandler
import io.github.smiley4.ktorswaggerui.dsl.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

@Suppress("unused")
@Controller(route = "rooms")
class RoomController(application: Application) : BaseController(application) {

    @Handler
    fun getAll(route: Route) {
        route.get({ description = "get all rooms" }) {
            try {
                call.respond(roomService.getAll().map { it.toPopulatedDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    @Handler
    fun getById(route: Route) {
        route.get("{id}", { description = "get room by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(roomService.getById(id).toPopulatedDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    @Handler
    fun getCommentsByRoom(route: Route) {
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

    @Handler
    fun create(route: Route) {
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

    @Handler
    fun updateById(route: Route) {
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

    @Handler
    fun deleteById(route: Route) {
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