package hotel.minimal.backend.core.annotations

@Target(AnnotationTarget.CLASS)
annotation class Controller(
    val route: String = "",
    val tags: Array<String> = [],
    val description: String = "",
    val summary: String = "",
    val securitySchemeName: String = "",
    val isActive: Boolean = true
)