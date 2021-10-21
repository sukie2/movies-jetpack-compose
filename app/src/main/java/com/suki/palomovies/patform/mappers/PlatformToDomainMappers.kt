package com.suki.palomovies.patform.mappers

import com.suki.palomovies.patform.api.response.MovieData
import com.suki.palomovies.patform.repository.data.Movie

fun MovieData.mapToDomain(): Movie = Movie(movieId = imdbID, title =  Title, posterUrl = Poster)
