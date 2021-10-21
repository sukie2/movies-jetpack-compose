package com.suki.palomovies.app.discover

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MovieDetailsScreen(navController: NavController, movieId: String) {
    Log.i("sdf---", movieId)
    LazyColumn() {
        // Add 5 items
        items(15) { index ->
            Text("Details", modifier = Modifier.height(100.dp))
        }
    }
}