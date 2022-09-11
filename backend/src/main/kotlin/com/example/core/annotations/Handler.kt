package com.example.core.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class Handler(val isActive: Boolean = true)