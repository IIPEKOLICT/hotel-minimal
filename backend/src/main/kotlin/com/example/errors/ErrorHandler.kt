package com.example.errors

import io.ktor.server.application.*
import io.ktor.server.response.*
import java.lang.Exception

object ErrorHandler {

    const val BAD_REQUEST = "Bad request"
    const val UNKNOWN_ERROR = "Unknown error"

    suspend fun handle(call: ApplicationCall, exception: Exception) {
        println("Exception message: ${exception.message}")

        return when(true) {
            (exception is EntityNotFoundError) -> {
                call.respond(exception.statusCode, exception.toDto())
            }
            (exception is BadRequestError) -> {
                call.respond(exception.statusCode, exception.toDto())
            }
            (exception is InternalServerError) -> {
                call.respond(exception.statusCode, exception.toDto())
            }
            else -> {
                val error = InternalServerError()
                call.respond(error.statusCode, error.toDto())
            }
        }
    }
}