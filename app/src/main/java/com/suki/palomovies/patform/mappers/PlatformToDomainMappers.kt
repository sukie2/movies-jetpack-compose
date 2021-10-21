package com.suki.palomovies.patform.mappers

import com.suki.palomovies.patform.api.response.MovieData
import com.suki.palomovies.patform.api.response.MovieDetailsResponse
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.patform.repository.data.MovieDetails

fun MovieData.mapToDomain(): Movie = Movie(movieId = imdbID?: "", title =  Title?: "", posterUrl = Poster)

fun MovieDetailsResponse.mapToDomain(): MovieDetails = MovieDetails(
    imdbID = imdbID ?: "",
    title = Title?: "",
    year= Year?: "",
    rated= Rated?: "",
    runtime= Runtime?: "",
    genre= Genre?: "",
    director= Director?: "",
    writer= Writer?: "",
    actors= Actors?: "",
    plot= Plot?: "",
    poster= Poster?: "",
    metascore= Metascore?: "",
    imdbRating= imdbRating?: "",
    imdbVotes= imdbVotes?: "",
)
