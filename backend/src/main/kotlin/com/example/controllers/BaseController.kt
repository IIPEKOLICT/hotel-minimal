package com.example.controllers

import com.example.dao.Services
import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.server.application.*
import io.ktor.server.routing.*

abstract class BaseController(private val application: Application) {

    private val handlers: MutableSet<(route: Route) -> Unit> = mutableSetOf()

    private var subRoute: String = ""
    private var swaggerCallback: OpenApiRoute.() -> Unit = {}

    protected val typeService = Services.typeService
    protected val roomService = Services.roomService
    protected val commentService = Services.commentService

    fun addHandler(handler: (route: Route) -> Unit) {
        handlers.add(handler)
    }

    fun setSwaggerCallback(newSwaggerCallback: OpenApiRoute.() -> Unit) {
        swaggerCallback = newSwaggerCallback
    }

    fun setSubRoute(newSubRoute: String) {
        subRoute = newSubRoute
    }

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