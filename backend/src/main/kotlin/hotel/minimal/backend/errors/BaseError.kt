package hotel.minimal.backend.errors

import io.ktor.http.*

abstract class BaseError(
    override val message: String = "",
    val statusCode: HttpStatusCode = HttpStatusCode.InternalServerError
): RuntimeException(message) {

    fun toDto(): Map<String, Any> {
        return mapOf(
            "code" to statusCode.value,
            "status" to statusCode.description,
            "message" to message
        )
    }
}