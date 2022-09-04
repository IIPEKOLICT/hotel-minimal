package com.example.errors

import io.ktor.http.*

class EntityNotFoundError(
    private val entityName: String = "Entity",
    private val id: Int = -1
): BaseError("$entityName with id $id not found", HttpStatusCode.NotFound)