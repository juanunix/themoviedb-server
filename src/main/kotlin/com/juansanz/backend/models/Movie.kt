package com.juansanz.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    val id: Long,
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    val status: String,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val adult: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val budget: Long,
    val homepage: String,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    val tagline: String,
    val genres: List<String>,
    @SerialName("production_companies")
    val productionCompanies: List<String>,
    @SerialName("production_countries")
    val productionCountries: List<String>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<String>,
    val keywords: List<String>,
)