package com.example.controllers

import com.example.annotations.Handler
import com.example.dao.services.TypeService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.util.*

class TypeController(application: Application) : Controller(application) {

    override val subRoute: String = "types"

    @Handler
    fun getAll() = get {
        it.call.respond(TypeService.getAll().map { type -> type.toDto() })
    }

    @Handler
    fun getById() = get("{id}") {
        val id: Int = it.call.parameters.getOrFail("id").toInt()
        it.call.respond(TypeService.getById(id).toDto())
    }
}