package hotel.minimal.backend.errors

import io.ktor.http.*

class BadRequestError(
    message: String = ErrorHandler.BAD_REQUEST
): BaseError(message, HttpStatusCode.BadRequest)