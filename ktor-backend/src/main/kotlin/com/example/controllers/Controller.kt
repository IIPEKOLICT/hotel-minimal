package com.example.controllers

import com.example.annotations.Handler
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

abstract class Controller(private val application: Application) {

    private suspend fun defaultErrorHandler(context: PipelineContext<Unit, ApplicationCall>, e: Exception) {
        context.call.respond(mapOf("message" to (e.message ?: "Unknown error")))
    }

    protected open val subRoute: String = ""

    protected fun get(handler: suspend (context: PipelineContext<Unit, ApplicationCall>) -> Unit) {
        application.routing {
            get(subRoute) {
                try {
                    handler(this)
                } catch (e: Exception) {
                    defaultErrorHandler(this, e)
                }
            }
        }
    }

    protected fun get(
        path: String,
        handler: suspend (context: PipelineContext<Unit, ApplicationCall>) -> Unit
    ) {
        application.routing {
            get(listOf(subRoute, path).joinToString("/")) {
                try {
                    handler(this)
                } catch (e: Exception) {
                    defaultErrorHandler(this, e)
                }
            }
        }
    }

    protected fun get(
        handler: suspend (context: PipelineContext<Unit, ApplicationCall>) -> Unit,
        errorHandler: suspend (
            context: PipelineContext<Unit, ApplicationCall>,
            e: Exception
        ) -> Unit = { context, e -> defaultErrorHandler(context, e) }
    ) {
        application.routing {
            get(subRoute) {
                try {
                    handler(this)
                } catch (e: Exception) {
                    errorHandler(this, e)
                }
            }
        }
    }

    protected fun get(
        path: String,
        handler: suspend (context: PipelineContext<Unit, ApplicationCall>) -> Unit,
        errorHandler: suspend (
            context: PipelineContext<Unit, ApplicationCall>,
            e: Exception
        ) -> Unit = { context, e -> defaultErrorHandler(context, e) }
    ) {
        application.routing {
            get(listOf(subRoute, path).joinToString("/")) {
                try {
                    handler(this)
                } catch (e: Exception) {
                    errorHandler(this, e)
                }
            }
        }
    }

    fun injectHandlers() {
        val controllerClass = this

        application.routing {
            controllerClass.javaClass.declaredMethods.forEach { method ->
                val hasHandlerAnnotation: Boolean = method.annotations.any { annotation ->
                    println(annotation?.annotationClass?.simpleName)
                    annotation?.annotationClass?.simpleName == Handler::class.simpleName
                }

                if (hasHandlerAnnotation) {
                    println(method)
                    method.invoke(controllerClass)
                }
            }
        }
    }
}