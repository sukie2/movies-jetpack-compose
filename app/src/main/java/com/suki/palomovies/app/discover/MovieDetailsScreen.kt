package com.suki.palomovies.app.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.suki.palomovies.R
import com.suki.palomovies.patform.components.HeaderText
import com.suki.palomovies.patform.components.ParagraphText
import com.suki.palomovies.patform.components.SubHeaderText
import com.suki.palomovies.ui.theme.PaloMoviesTheme
import com.suki.palomovies.ui.theme.Purple700

@Composable
fun MovieDetailsScreen(navController: NavController, movieId: String) {
    Box(
        modifier = Modifier
            .background(Purple700)
            .fillMaxSize()
    ) {
        LazyColumn() {
            item {
                PosterSection()
                InfoSection()
                CrewSection()
            }
        }
    }
}

@Composable
fun CrewSection() {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base_2x))
            .fillMaxWidth(),
    ) {
        SubHeaderText("${stringResource(id = R.string.director)} director")
        SubHeaderText("${stringResource(id = R.string.writer)} director")
        SubHeaderText("${stringResource(id = R.string.actors)} director")
    }
}

@Composable
fun InfoSection() {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base_2x))
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SubHeaderText("2021")
            SubHeaderText("2h 10m")
            Row {
                Icon(
                    Icons.Filled.Star, "StarImage",
                    tint = Color.Yellow
                )
                SubHeaderText("7.5")
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base)))
        HeaderText(stringResource(id = R.string.synopsis))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base)))
        ParagraphText(
            "Marvel's \\\"Doctor Strange\\\" follows the story of " +
                    "the talented neurosurgeon Doctor Stephen Strange who, after a tragic car accident," +
                    " must put ego aside and learn the secrets of a hidden world of mysticism and alternate dimensions." +
                    " Based in New York City's Greenwich Village, Doctor Strange must act as an intermediary between the real world" +
                    " and what lies beyond, utilising a vast array of metaphysical abilities and artifacts to protect the Marvel Cinematic Universe."
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base_2x)))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText("2021")
                SubHeaderText("2h 10m")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText("2021")
                SubHeaderText("2h 10m")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText("2021")
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        Icons.Filled.ArrowDropDown, "ArrowImage",
                        tint = Color.Cyan
                    )
                    SubHeaderText("7.5")
                }
            }
        }

    }
}

@Composable
fun PosterSection() {
    Box {
        GlideImage(
            imageModel = "https://m.media-amazon.com/images/M/MV5BODZkM2I4ZGUtZDBhZS00ODEyLTljMzQtYWFjOGVjZWI1MTYxXkEyXkFqcGdeQXVyMjcyNTc3NzA@._V1_SX300.jpg",
            contentScale = ContentScale.FillWidth,
            circularReveal = CircularReveal(duration = 250),
            placeHolder = ImageBitmap.imageResource(R.drawable.movie_placeholder),
            error = ImageBitmap.imageResource(R.drawable.movie_placeholder),
            modifier = Modifier
                .height(300.dp),
            requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL),
            alignment = Alignment.Center,
        )
        Column(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.spacing_base_2x))
                .align(BottomStart)
        ) {
            HeaderText("Title")
            SubHeaderText("2021")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    PaloMoviesTheme {
        PosterSection()
    }
}