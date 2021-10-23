package com.suki.palomovies.app.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.suki.palomovies.R
import com.suki.palomovies.patform.components.HeaderText
import com.suki.palomovies.patform.components.ParagraphText
import com.suki.palomovies.patform.components.SubHeaderText
import com.suki.palomovies.patform.repository.data.MovieDetails
import com.suki.palomovies.ui.theme.Purple700
import com.suki.palomovies.ui.theme.TextWhite

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: String
) {
    viewModel.getMovieDetails(movieId)
    val movieDetails = viewModel.movieDetails.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Purple700)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                PosterSection(movieDetails = movieDetails)
                InfoSection(movieDetails = movieDetails)
                CrewSection(movieDetails = movieDetails)
            }
        }
        if (viewModel.isFetching.value) {
            CircularProgressIndicator(
                color = TextWhite,
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.spacing_base_2x))
                    .height(dimensionResource(id = R.dimen.spacing_base_3x))
                    .width(dimensionResource(id = R.dimen.spacing_base_3x))
            )
        }
    }
}

@Composable
fun CrewSection(movieDetails: MovieDetails) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base_2x))
            .fillMaxWidth(),
    ) {
        SubHeaderText("${stringResource(id = R.string.director)} ${movieDetails.director}")
        SubHeaderText("${stringResource(id = R.string.writer)} ${movieDetails.writer}")
        SubHeaderText("${stringResource(id = R.string.actors)} ${movieDetails.actors}")
    }
}

@Composable
fun InfoSection(movieDetails: MovieDetails) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base_2x))
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            SubHeaderText(
                movieDetails.genre,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            SubHeaderText(
                movieDetails.runtime,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                Icon(
                    Icons.Filled.Star, "StarImage",
                    tint = Color.Yellow
                )
                SubHeaderText(movieDetails.imdbRating)
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base)))
        HeaderText(stringResource(id = R.string.synopsis))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base)))
        ParagraphText(movieDetails.plot)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base_2x)))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText(stringResource(id = R.string.score))
                SubHeaderText(movieDetails.metascore)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText(stringResource(id = R.string.reviews))
                SubHeaderText(movieDetails.imdbVotes)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SubHeaderText(stringResource(id = R.string.rate))
                SubHeaderText(movieDetails.rated)
            }
        }
    }
}

@Composable
fun PosterSection(movieDetails: MovieDetails) {
    Box {
        GlideImage(
            imageModel = movieDetails.poster,
            contentScale = ContentScale.FillWidth,
            circularReveal = CircularReveal(duration = 250),
            placeHolder = ImageBitmap.imageResource(R.drawable.movie_placeholder_wide),
            error = ImageBitmap.imageResource(R.drawable.movie_placeholder_wide),
            modifier = Modifier
                .height(300.dp),
            requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL),
            alignment = Alignment.Center,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        (
                                listOf(Color.Transparent, Color.Black)),
                        0f,
                        200f,
                    )
                )
                .padding(all = dimensionResource(id = R.dimen.spacing_base_2x))
                .align(BottomStart)
        ) {
            HeaderText(movieDetails.title)
            SubHeaderText(movieDetails.year)
        }
    }
}