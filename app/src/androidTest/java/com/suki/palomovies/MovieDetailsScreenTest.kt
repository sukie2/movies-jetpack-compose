package com.suki.palomovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.suki.palomovies.app.discover.MovieDetailsScreen
import com.suki.palomovies.app.discover.MovieSearchScreen
import org.junit.Rule
import org.junit.Test

class MovieDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @Test
    fun given_compose_view_has_desired_header_text() {

        //FIXME: In progress

        composeTestRule.setContent {
            val navController = rememberNavController()
            MovieSearchScreen(navController = navController)
        }

        composeTestRule.onNode(
            hasText("12.59"),
            useUnmergedTree = true
        ).assertExists()
    }
}
