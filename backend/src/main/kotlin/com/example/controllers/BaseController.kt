package com.example.controllers

import com.example.dao.Services
import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.server.application.*
import io.ktor.server.routing.*

abstract class BaseController(
    private val application: Application,
    private val subRoute: String
) {

    protected open val handlers: Set<(route: Route) -> Unit>
        get() = setOf()

    protected open val swaggerCallback: OpenApiRoute.() -> Unit
        get() = {}

    protected constructor(application: Application) : this(application, "")

    protected val typeService = Services.typeService
    protected val roomService = Services.roomService
    protected val commentService = Services.commentService

    fun inject() {
        application.routing {
            route(subRoute, swaggerCallback) {
                handlers.forEach {
                    it(this)
                }
            }
        }
    }
}