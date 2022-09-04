package com.example

import io.ktor.http.*
import io.ktor.client.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.example.controllers.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configure()
            mainController()
            typeController()
            roomController()
            commentController()
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertContains(mapOf("status" to "ok"), "status", "ok")
        }
    }
}