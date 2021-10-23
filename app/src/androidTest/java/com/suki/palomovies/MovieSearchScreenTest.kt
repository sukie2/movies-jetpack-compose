package com.suki.palomovies

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.suki.palomovies.app.MainActivity
import com.suki.palomovies.app.discover.MovieSearchScreen
import com.suki.palomovies.app.discover.MovieSearchViewModel
import com.suki.palomovies.ui.theme.PaloMoviesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MovieSearchScreenTest {

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
                val navController = rememberNavController()
                MovieSearchScreen(
                    navController = navController,
                    viewModel = composeTestRule.activity.viewModels<MovieSearchViewModel>().value)
            }
        }

        composeTestRule.onNode(
            hasText("Start by typing a movie name"),
            useUnmergedTree = true
        ).assertExists()
    }
}
