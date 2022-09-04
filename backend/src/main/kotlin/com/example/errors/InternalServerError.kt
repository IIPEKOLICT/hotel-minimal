package com.example.errors

import io.ktor.http.*

class InternalServerError(
    message: String = ErrorHandler.UNKNOWN_ERROR
): BaseError(message, HttpStatusCode.InternalServerError)