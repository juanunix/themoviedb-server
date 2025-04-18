package com.juansanz.backend.plugins

import com.juansanz.backend.repositories.MovieRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.movies() {

    val movieRepository = MovieRepositoryImpl()

    routing {
        route("/movies") {

            get {
                call.respond(movieRepository.getAll())
            }

            get("/{id}") {
                val id = call.parameters["id"]
                val movie = id?.let { movieRepository.getById(it.toLong()) }
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }

            get("/searchByTitle/{title}") {
                val title = call.parameters["title"]
                val movie = title?.let { movieRepository.searchByTitle(title) }
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }

            get("/filterByGenre/{gen}") {
                val gen = call.parameters["gen"]
                val movie = gen?.let { movieRepository.filterByGenre(gen) }
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }
            /*
            get("/filterByMinRating/{rating}") {
                val rating = call.parameters["rating"]
                val movie = rating?.let { movieRepository.filterByMinRating(rating) }
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }
            get("/filterByYear/{year}") {
                val year = call.parameters["year"]
                val movie = year?.let { movieRepository.filterByYear(year) }
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }
            */
            get("/topNPopular") {
                val movie = movieRepository.topNPopular()
                if (movie != null) {
                    call.respond(movie)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }

            post {
                val movie = call.receive<com.juansanz.backend.models.MovieEntity>()
                movieRepository.save(movie)
                call.respondText("Movie added successfully", status = HttpStatusCode.Created)
            }

            put("/{id}") {
                val id = call.parameters["id"]
                val updatedMovie = call.receive<com.juansanz.backend.models.MovieEntity>()
                if (id != null && movieRepository.update(id, updatedMovie)) {
                    call.respondText("Movie updated successfully", status = HttpStatusCode.OK)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                if (id != null && movieRepository.delete(id.toLong())) {
                    call.respondText("Movie deleted successfully", status = HttpStatusCode.OK)
                } else {
                    call.respondText("Movie not found", status = HttpStatusCode.NotFound)
                }
            }

        }
    }
}