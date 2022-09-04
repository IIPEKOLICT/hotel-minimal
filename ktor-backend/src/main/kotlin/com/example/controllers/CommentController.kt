package com.example.controllers

import com.example.dao.services.CommentService
import com.example.dao.services.RoomService
import com.example.dtos.CommentDto
import com.example.dtos.DeleteDto
import com.example.errors.ErrorHandler
import com.example.models.entities.Comment
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.commentController() {
    routing {
        get("/comments/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(CommentService.getById(id).toDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }

        post("/comments") {
            try {
                val dto: CommentDto = call.receive()

                val comment: Comment = CommentService.create(
                    dto = dto,
                    newRoom = RoomService.getById(dto.room)
                )

                call.respond(comment.toDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }

        put("/comments/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: CommentDto = call.receive()

                val comment: Comment = CommentService.change(id = id, dto = dto)

                call.respond(comment.toDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }

        delete("/comments/{id}") {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(DeleteDto(id = CommentService.delete(id)))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}