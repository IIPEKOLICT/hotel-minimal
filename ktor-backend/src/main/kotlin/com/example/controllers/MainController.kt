package com.example.controllers

import com.example.annotations.Handler
import io.ktor.server.application.*
import io.ktor.server.response.*

class MainController(application: Application) : Controller(application) {

    @Handler
    fun healthCheck() = get {
        it.call.respond(mapOf("status" to "ok"))
    }

//    suspend fun healthCheck() = get {
//        it.call.respond(mapOf("status" to "ok"))
//    }
}