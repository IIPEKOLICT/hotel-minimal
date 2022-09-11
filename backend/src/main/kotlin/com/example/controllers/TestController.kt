package com.example.controllers

import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class TestController(application: Application) : BaseController(application, "test") {

    override val handlers: Set<(route: Route) -> Unit>
        get() = setOf { healthCheck(it) }

    override val swaggerCallback: OpenApiRoute.() -> Unit
        get() = {
            tags = listOf("test")
            description = "Test controller"
        }

    private fun healthCheck(route: Route) {
        route.get({ description = "Check backend state" }) {
            call.respond(mapOf("status" to "ok"))
        }
    }
}