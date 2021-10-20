package com.suki.palomovies.app

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.suki.palomovies.R

sealed class Routes(
    val route: String
) {
    object MovieSearch :
        Routes(
            "movieSearch"
        )

    object MovieDetails :
        Routes(
            "details"
        )
}

//sealed class Screens(
//    val route: String,
//    @StringRes val resourceId: Int,
//    val icon: ImageVector,
//    @StringRes val iconDescription: Int
//) {
//    object MovieSearchPage :
//        Screens(
//            Routes.MovieSearch.route,
//            R.string.nav_bottom_bar_home,
//            Icons.Default.Home,
//            R.string.nav_bottom_bar_home
//        )
//
//    object MovieDetailsPage :
//        Screens(
//            Routes.CurrenciesRoute.route,
//            R.string.nav_bottom_bar_list,
//            Icons.Default.List,
//            R.string.nav_bottom_bar_list
//        )
//}
