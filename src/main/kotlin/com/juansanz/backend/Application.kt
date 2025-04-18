package com.juansanz.backend

import com.juansanz.backend.plugins.configureRouting
import com.juansanz.backend.plugins.configureSerialization
import com.juansanz.backend.plugins.movies
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        movies()
        configureSerialization()
        install(CORS) {
            allowMethod(HttpMethod.Delete)
            allowMethod(HttpMethod.Put)
            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowNonSimpleContentTypes = true
            anyHost()
        }
    }.start(wait = true)
}
