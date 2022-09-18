package hotel.minimal.backend.core.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class Handler(val isActive: Boolean = true)