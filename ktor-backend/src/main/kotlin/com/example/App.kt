package com.example

import com.example.data.DatabaseManager
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.configure() {
    install(CallLogging)

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    DatabaseManager.init(environment.config)
}