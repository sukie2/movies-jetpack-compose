package com.suki.palomovies.patform.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    val imdbID: String? = "",
    val Title: String? = "",
    val Year: String? = "",
    val Rated: String? = "",
    val Runtime: String? = "",
    val Genre: String? = "",
    val Director: String? = "",
    val Writer: String? = "",
    val Actors: String? = "",
    val Plot: String? = "",
    val Poster: String? = "",
    val Metascore: String? = "",
    val imdbRating: String? = "",
    val imdbVotes: String? = "",
)