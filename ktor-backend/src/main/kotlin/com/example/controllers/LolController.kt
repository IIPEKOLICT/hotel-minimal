package com.example.controllers

import com.example.annotations.Controller
import com.example.annotations.Get
import io.ktor.server.application.*
import io.ktor.server.response.*

@Controller("lol")
class LolController {

    @Get("lol")
    fun getAll(call: ApplicationCall) {
        call.respond("Worked!")
    }
}