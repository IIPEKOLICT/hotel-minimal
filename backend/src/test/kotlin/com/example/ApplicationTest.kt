package com.example

import io.ktor.http.*
import io.ktor.client.request.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            launch()
        }

        client.get("/test").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertContains(mapOf("status" to "ok"), "status", "ok")
        }
    }
}