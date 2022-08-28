package com.example.core

import com.example.annotations.Controller
import com.example.annotations.Get
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.reflect.Method

class Reflector(private val application: Application) {

    private suspend fun defaultErrorHandler(call: ApplicationCall, e: Exception) {
        call.respond(HttpStatusCode.InternalServerError, mapOf("message" to (e.message ?: "Unknown error")))
    }

    private fun getFullPath(controllerPath: String, handlerPath: String): String {
        return if (handlerPath.isNotBlank()) {
            listOf(controllerPath, handlerPath).joinToString("/")
        } else {
            controllerPath
        }
    }

    fun launch(vararg controllers: Any) {
        application.routing {
            controllers.forEach { controller ->
                controller.javaClass.isAnnotationPresent(Controller::class.java) || return@forEach

                val controllerAnnotation: Controller = controller.javaClass.getAnnotation(Controller::class.java)

                controller.javaClass.declaredMethods.forEach { method: Method ->
                    when(true) {
                        method.isAnnotationPresent(Get::class.java) -> {
                            val fullPath: String = getFullPath(
                                controllerAnnotation.subRoute,
                                method.getAnnotation(Get::class.java).path
                            )

                            get(fullPath) {
                                try {
                                    method.invoke(controller, call)
                                } catch (e: Exception) {
                                    println(e)
                                    defaultErrorHandler(call, e)
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}