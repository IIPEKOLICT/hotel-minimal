package com.example.annotations

@Target(AnnotationTarget.CLASS)
annotation class Controller(val subRoute: String = "")
