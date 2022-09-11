package com.example

import com.example.controllers.*
import com.example.dao.DatabaseManager
import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.configurePlugins() {
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

    install(SwaggerUI) {
        swagger {
            forwardRoot = true
        }

        info {
            title = "Hotel-minimal"
            version = "latest"
            description = "Hotel-minimal API documentation"

            contact {
                name = "API Support"
                url = "https://github.com/IIPEKOLICT"
                email = "iipekolict@gmail.com"
            }

            license {
                name = "MIT"
                url = "https://raw.githubusercontent.com/IIPEKOLICT/hotel-minimal/main/LICENSE"
            }
        }

        server {
            url = "http://localhost:5000"
            description = "Development server"
        }

        System.getenv("BACKEND_URL")?.let {
            server {
                url = it
                description = "Production server"
            }
        }
    }
}

fun Application.init() {
    DatabaseManager.init(environment.config)

    val controllers: Set<BaseController> = setOf(
        TestController(this),
        CommentController(this),
        TypeController(this),
        RoomController(this)
    )

    controllers.forEach {
        with(it) {
            inject()
        }
    }
}

fun Application.launch() {
    configurePlugins()
    init()
}