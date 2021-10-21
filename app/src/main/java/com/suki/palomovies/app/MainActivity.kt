package com.suki.palomovies.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suki.palomovies.app.discover.MovieDetailsScreen
import com.suki.palomovies.app.discover.MovieSearchScreen
import com.suki.palomovies.ui.theme.PaloMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PaloMoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieSearch.route
                    ) {
                        composable(Screen.MovieSearch.route) { MovieSearchScreen(navController = navController) }
                        composable(route = Screen.MovieDetails.route,) { backStackEntry ->
                            val movieId = backStackEntry.arguments?.getString("movieId")
                            requireNotNull(movieId) { "movieId parameter wasn't found. Please make sure it's set!" }
                            MovieDetailsScreen(navController, movieId)
                        }
                    }
                }
            }
        }
    }
}

