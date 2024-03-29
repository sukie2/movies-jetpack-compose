package com.suki.palomovies

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.suki.palomovies.app.MainActivity
import com.suki.palomovies.app.discover.MovieDetailsScreen
import com.suki.palomovies.app.discover.MovieDetailsViewModel
import com.suki.palomovies.ui.theme.PaloMoviesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MovieDetailsScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()

    }

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @Test
    fun given_compose_view_has_desired_header_text() {

        composeTestRule.setContent {
            PaloMoviesTheme(isNetworkAvailable = true) {
                MovieDetailsScreen(
                    movieId = "test12345",
                    viewModel = composeTestRule.activity.viewModels<MovieDetailsViewModel>().value
                )
            }
        }

        composeTestRule.onNode(
            hasText("Synopsis"),
            useUnmergedTree = true
        ).assertExists()
    }
}
