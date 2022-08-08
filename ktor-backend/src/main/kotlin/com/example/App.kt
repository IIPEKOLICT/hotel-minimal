package com.example

import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.configure() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    install(ContentNegotiation) {
        gson {}
    }
}