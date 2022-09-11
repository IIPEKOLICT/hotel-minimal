package loshica.hotel.shared

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class Ktor {
    private val httpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

//        install(Gson) {
//
//        }

        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_DURATION
            connectTimeoutMillis = TIMEOUT_DURATION
            requestTimeoutMillis = TIMEOUT_DURATION
        }

        defaultRequest {
            if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    companion object {
        const val TIMEOUT_DURATION = 15000L
    }
}