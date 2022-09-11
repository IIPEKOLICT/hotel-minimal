package com.example.controllers

import com.example.db.entities.Comment
import com.example.dtos.CommentDto
import com.example.dtos.DeleteDto
import com.example.errors.ErrorHandler
import io.github.smiley4.ktorswaggerui.dsl.*
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.github.smiley4.ktorswaggerui.dsl.put
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

class CommentController(application: Application) : BaseController(application, "comments") {
    override val handlers: Set<(route: Route) -> Unit>
        get() = setOf(
            { getById(it) },
            { create(it) },
            { updateById(it) },
            { deleteById(it) }
        )

    override val swaggerCallback: OpenApiRoute.() -> Unit
        get() = {
            tags = listOf("comments")
            description = "Comment controller"
        }

    private fun getById(route: Route) {
        route.get("{id}", { description = "get comment by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(commentService.getById(id).toDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun create(route: Route) {
        route.post({ description = "create comment" }) {
            try {
                val dto: CommentDto = call.receive()

                val comment: Comment = commentService.create(
                    dto = dto,
                    newRoom = roomService.getById(dto.room)
                )

                call.respond(comment.toDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }
    }

    private fun updateById(route: Route) {
        route.put("{id}", { description = "update comment by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: CommentDto = call.receive()

                val comment: Comment = commentService.change(id = id, dto = dto)

                call.respond(comment.toDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }
    }

    private fun deleteById(route: Route) {
        route.delete("{id}", { description = "delete comment by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(DeleteDto(id = commentService.delete(id)))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}