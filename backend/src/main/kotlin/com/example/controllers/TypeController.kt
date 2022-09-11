package com.example.controllers

import com.example.dtos.DeleteDto
import com.example.dtos.TypeDto
import com.example.errors.ErrorHandler
import io.github.smiley4.ktorswaggerui.dsl.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

class TypeController(application: Application) : BaseController(application, "types") {
    override val handlers: Set<(route: Route) -> Unit>
        get() = setOf(
            { getAll(it) },
            { getById(it) },
            { create(it) },
            { updateById(it) },
            { deleteById(it) }
        )

    override val swaggerCallback: OpenApiRoute.() -> Unit
        get() = {
            tags = listOf("types")
            description = "Type controller"
        }

    private fun getAll(route: Route) {
        route.get({ description = "get all types" }) {
            try {
                call.respond(typeService.getAll().map { it.toDto() })
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun getById(route: Route) {
        route.get("{id}", { description = "get type by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(typeService.getById(id).toDto())
            } catch (e: Exception) {
                return@get ErrorHandler.handle(call, e)
            }
        }
    }

    private fun create(route: Route) {
        route.post({ description = "create type" }) {
            try {
                val dto: TypeDto = call.receive()
                call.respond(typeService.create(dto).toDto())
            } catch (e: Exception) {
                return@post ErrorHandler.handle(call, e)
            }
        }
    }

    private fun updateById(route: Route) {
        route.put("{id}", { description = "update type by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                val dto: TypeDto = call.receive()
                call.respond(typeService.change(id, dto).toDto())
            } catch (e: Exception) {
                return@put ErrorHandler.handle(call, e)
            }
        }
    }

    private fun deleteById(route: Route) {
        route.delete("{id}", { description = "delete type by id" }) {
            try {
                val id: Int = call.parameters.getOrFail("id").toInt()
                call.respond(DeleteDto(id = typeService.delete(id)))
            } catch (e: Exception) {
                return@delete ErrorHandler.handle(call, e)
            }
        }
    }
}