package com.suki.palomovies.patform.repository

import com.suki.palomovies.patform.api.MovieApi
import com.suki.palomovies.patform.mappers.mapToDomain
import com.suki.palomovies.patform.repository.data.Movie
import javax.inject.Inject

interface MovieRepository {
    suspend fun searchMovie(query: String, type: String, page: Int): List<Movie>
}

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun searchMovie(query: String, type: String, page: Int): List<Movie> =
        movieApi.searchMovie(query = query, type = type, page = page).Search.map {
            it.mapToDomain()
        }
}

