package com.juansanz.backend.repositories


import com.juansanz.backend.database.TMDB_movie_dataset_v11
import com.juansanz.backend.models.MovieEntity
import com.juansanz.themoviedb.database.AppDatabase
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

private const val DATABASE_NAME = "database.db"

class MovieRepositoryImpl : MovieRepository {

    private val movieDb = JdbcSqliteDriver(url = "jdbc:sqlite:$DATABASE_NAME").let {
        if (!File(DATABASE_NAME).exists()) {
            AppDatabase.Schema.create(it)
        }
        AppDatabase(it)
    }.moviesQueries

    override fun getAll(): List<MovieEntity> =
        movieDb.select().executeAsList().map { it.toMovie() }

    override fun getById(id: Long): MovieEntity? =
        movieDb.selectbyId(id).executeAsOneOrNull()?.toMovie()

    override fun save(movie: MovieEntity): MovieEntity {
        movieDb.insert(
            id = movie.id,
            title = movie.title,
            vote_average = movie.voteAverage,
            vote_count = movie.voteCount.toLong(),
            status = movie.status,
            release_date = movie.releaseDate,
            revenue = movie.revenue,
            runtime = movie.runtime.toLong(),
            adult = movie.adult,
            backdrop_path = movie.backdropPath,
            budget = movie.budget,
            homepage = movie.homepage,
            imdb_id = movie.imdbId,
            original_language = movie.originalLanguage,
            original_title = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            poster_path = movie.posterPath,
            genres = movie.genres.toFormattedString(),
            tagline = movie.tagline,
            production_companies = movie.productionCompanies.toFormattedString(),
            production_countries = movie.productionCountries.toFormattedString(),
            spoken_languages = movie.spokenLanguages.toFormattedString(),
            keywords = movie.keywords.toFormattedString()

        )
        return movieDb.selectLastInsertedNote().executeAsOne().toMovie()
    }

    override fun update(id: String, updatedMovie: MovieEntity): Boolean {
        if (NotesRepository.getById(updatedMovie.id) == null) return false
        movieDb.update(
            id = updatedMovie.id,
            title = updatedMovie.title,
            vote_average = updatedMovie.voteAverage,
            vote_count = updatedMovie.voteCount.toLong(),
            status = updatedMovie.status,
            release_date = updatedMovie.releaseDate,
            revenue = updatedMovie.revenue,
            runtime = updatedMovie.runtime.toLong(),
            adult = updatedMovie.adult,
            backdrop_path = updatedMovie.backdropPath,
            budget = updatedMovie.budget,
            homepage = updatedMovie.homepage,
            imdb_id = updatedMovie.imdbId,
            original_language = updatedMovie.originalLanguage,
            original_title = updatedMovie.originalTitle,
            overview = updatedMovie.overview,
            popularity = updatedMovie.popularity,
            poster_path = updatedMovie.posterPath,
            genres = updatedMovie.genres.toFormattedString(),
            tagline = updatedMovie.tagline,
            production_companies = updatedMovie.productionCompanies.toFormattedString(),
            production_countries = updatedMovie.productionCountries.toFormattedString(),
            spoken_languages = updatedMovie.spokenLanguages.toFormattedString(),
            keywords = updatedMovie.keywords.toFormattedString(),
            id_ = updatedMovie.id,
        )
        return true
    }

    override fun delete(id: Long): Boolean {
        if (NotesRepository.getById(id) == null) return false
        movieDb.delete(id)
        return true
    }

    override fun searchByTitle(title: String): List<MovieEntity> =
        movieDb.searchByTitle(title).executeAsList().map { it.toMovie() }

    override fun filterByGenre(genre: String): List<MovieEntity> =
        movieDb.filterByGenre(genre).executeAsList().map { it.toMovie() }

    override fun topNPopular(): List<MovieEntity> =
        movieDb.topNPopular().executeAsList().map { it.toMovie() }

    /*
    fun List<Movie>.searchByTitle(query: String): List<Movie> {
        return this.filter { it.title.contains(query, ignoreCase = true) }
    }
    fun List<Movie>.filterByGenre(genre: String): List<Movie> {
        return this.filter { genre.lowercase() in it.genres.map(String::lowercase) }
    }
    fun List<Movie>.filterByYear(year: Int): List<Movie> {
        return this.filter { it.releaseDate.startsWith("$year") }
    }
    fun List<Movie>.filterByMinRating(minRating: Double): List<Movie> {
        return this.filter { it.voteAverage >= minRating }
    }
    fun List<Movie>.filterByLanguage(langCode: String): List<Movie> {
        return this.filter { it.language.equals(langCode, ignoreCase = true) }
    }
    ⭐ 6. Top N más populares
    fun List<Movie>.topNPopular(n: Int): List<Movie> {
        return this.sortedByDescending { it.popularity }.take(n)
    }
    
    Extra: Combinación de filtros
    
    val results = movies
        .searchByTitle("love")
        .filterByGenre("romance")
        .filterByMinRating(7.0)
        .filterByYear(2020)
        .topNPopular(10)
    
    */
}

private fun TMDB_movie_dataset_v11.toMovie(): MovieEntity =
    MovieEntity(
        id = id!!,
        title = title.orEmpty(),
        voteAverage = vote_average!!,
        voteCount = vote_count?.toInt() ?: 0,
        status = status.orEmpty(),
        releaseDate = release_date.orEmpty(),
        revenue = revenue!!,
        runtime = runtime?.toInt() ?: 0,
        adult = adult.orEmpty(),
        backdropPath = backdrop_path.orEmpty(),
        budget = budget!!,
        homepage = homepage.orEmpty(),
        imdbId = imdb_id.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        originalTitle = original_title.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity!!,
        posterPath = poster_path.orEmpty(),
        tagline = tagline.orEmpty(),
        genres = genres?.toList() ?: emptyList(),
        productionCompanies = production_companies?.toList() ?: emptyList(),
        productionCountries = production_countries?.toList() ?: emptyList(),
        spokenLanguages = spoken_languages?.toList() ?: emptyList(),
        keywords = keywords?.toList() ?: emptyList()
    )

fun List<String>.toFormattedString(separator: String = ", "): String {
    return this.joinToString(separator)
}

fun String.toList(): List<String> {
    return this.split(", ").map { it.trim() }
}

interface MovieRepository {
    fun getAll(): List<MovieEntity>
    fun getById(id: Long): MovieEntity?
    fun save(movie: MovieEntity): MovieEntity
    fun update(id: String, updatedMovie: MovieEntity): Boolean
    fun delete(id: Long): Boolean

    fun searchByTitle(title: String): List<MovieEntity>
    fun filterByGenre(genre: String): List<MovieEntity>
    fun topNPopular(): List<MovieEntity>

}