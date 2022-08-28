package com.example.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class Get(val path: String = "")
