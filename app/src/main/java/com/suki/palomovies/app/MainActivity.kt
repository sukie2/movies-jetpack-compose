package com.suki.palomovies.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
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
            PaloMoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieSearchScreen()
                }
            }
        }
    }
}