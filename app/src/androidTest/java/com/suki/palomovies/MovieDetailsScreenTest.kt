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

    //FIXME: In progress

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @Test
    fun given_bitcoin_price_is_known_when_app_retrieves_bitcoin_price_then_show_bitcoin_price() {

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
