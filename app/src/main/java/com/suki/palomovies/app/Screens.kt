package com.suki.palomovies.app

//object Screens {
//    const val MOVIE_SEARCH = "MovieSearch"
//    const val MOVIE_DETAILS = "MovieDetails/{movieId}"
//}

sealed class Screen(val route: String) {
    object MovieSearch: Screen("movieSearch")
    object MovieDetails: Screen("movieDetails/{movieId}") {
        fun createRoute(movieId: String) = "movieDetails/$movieId"
    }
}
