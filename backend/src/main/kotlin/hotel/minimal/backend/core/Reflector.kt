package hotel.minimal.backend.core

import hotel.minimal.backend.controllers.BaseController
import hotel.minimal.backend.core.annotations.Controller
import hotel.minimal.backend.core.annotations.Handler

class Reflector {

    private var controllers: Set<BaseController> = setOf()

    fun setControllers(vararg controllers: BaseController): Reflector {
        this.controllers = controllers.toSet()
        return this
    }

    fun buildRoutes() {
        controllers.forEach {
            with(it) {
                val controller = this

                try {
                    val controllerAnnotation = controller
                        .javaClass
                        .getDeclaredAnnotation(Controller::class.java)

                    if (controllerAnnotation.isActive) {
                        controller.setSubRoute(controllerAnnotation.route)

                        val swaggerTags: List<String> = if(
                            controllerAnnotation.route.isNotBlank() &&
                            controllerAnnotation.tags.isEmpty()
                        ) {
                            listOf(controllerAnnotation.route)
                        } else {
                            controllerAnnotation.tags.toList()
                        }

                        controller.setSwaggerCallback {
                            tags = swaggerTags
                            description = controllerAnnotation.description
                            summary = controllerAnnotation.summary
                            securitySchemeName = controllerAnnotation.securitySchemeName
                        }
                    }
                } catch (_: Exception) {}

                javaClass.declaredMethods.forEach { method ->
                    try {
                        val handlerAnnotation = method.getDeclaredAnnotation(Handler::class.java)

                        if (handlerAnnotation.isActive) {
                            controller.addHandler { route ->
                                method(controller, route)
                            }
                        }
                    } catch (_: Exception) {}
                }

                inject()
            }
        }
    }
}