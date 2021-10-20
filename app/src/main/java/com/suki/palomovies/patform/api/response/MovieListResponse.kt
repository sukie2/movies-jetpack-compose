package com.suki.palomovies.patform.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    val Search: List<MovieData>
)

@JsonClass(generateAdapter = true)
data class MovieData(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String

)
