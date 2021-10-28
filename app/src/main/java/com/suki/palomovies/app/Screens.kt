package com.suki.palomovies.app

sealed class Screen(val route: String) {
    object MovieSearch: Screen("movieSearch")
    object MovieDetails: Screen("movieDetails/{movieId}") {
        fun createRoute(movieId: String) = "movieDetails/$movieId"
    }
}
