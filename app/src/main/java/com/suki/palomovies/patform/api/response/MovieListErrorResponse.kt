package com.suki.palomovies.patform.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListErrorResponse(
    val Response: String,
    val Error: String
)