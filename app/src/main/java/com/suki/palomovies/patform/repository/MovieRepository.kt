package com.suki.palomovies.patform.repository

import com.suki.palomovies.patform.api.MovieApi
import com.suki.palomovies.patform.mappers.mapToDomain
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.patform.repository.data.MovieDetails
import javax.inject.Inject

interface MovieRepository {
    suspend fun searchMovie(query: String, type: String, page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: String, plot: String): MovieDetails
}

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun searchMovie(query: String, type: String, page: Int): List<Movie> {
        val result = movieApi.searchMovie(query = query, type = type, page = page)
        if (result.Search?.isNotEmpty() == true) {
            return result.Search.map {
                it.mapToDomain()
            }
        }
        return arrayListOf()
    }

    override suspend fun getMovieDetails(movieId: String, plot: String): MovieDetails {
        val result = movieApi.getMovieDetails(movieId = movieId, plot = plot)
            return result.mapToDomain()
    }
}

