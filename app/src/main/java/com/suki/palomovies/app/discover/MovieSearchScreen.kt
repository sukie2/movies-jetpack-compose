package com.suki.palomovies.app.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.ui.theme.Purple200
import com.suki.palomovies.ui.theme.Purple700
import com.suki.palomovies.ui.theme.TextWhite
import com.suki.palomovies.R
import com.suki.palomovies.ui.theme.Purple500

@ExperimentalFoundationApi
@Composable
fun MovieSearchScreen() {
    Box(modifier = Modifier.background(Purple700)){
        FeatureSection(features = listOf(
            Movie(
                title = "Sleep meditation",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BODllNjUxMDctYWE2ZS00NGM0LThlZDUtNTNiMTFlMmM2ZWQyXkEyXkFqcGdeQXVyNTE1NjY5Mg@@._V1_SX300.jpg"
            ),
            Movie(
                title = "Sleep meditation",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BZjNkNTJhNTktNzNkNi00MTk0LWIyNzUtNTMxMGQwMzM3NDhiXkEyXkFqcGdeQXVyMjY2OTU0MTg@._V1_SX300.jpg"
            ),
            Movie(
                title = "Sleep meditation",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg0MzgyMzM2MV5BMl5BanBnXkFtZTgwMDI2MTU1NzE@._V1_SX300.jpg"
            ),
            Movie(
                title = "Sleep meditatdfds ds df ds f dfion",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BZTAyMTVmNjMtMWQ4ZS00NzJmLWI1ODUtMjU5MWU2ZWU2NzYzXkEyXkFqcGdeQXVyMjQ0OTA1Nzc@._V1_SX300.jpg"
            ),
            Movie(
                title = "Sleep meditation",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BZTAyMTVmNjMtMWQ4ZS00NzJmLWI1ODUtMjU5MWU2ZWU2NzYzXkEyXkFqcGdeQXVyMjQ0OTA1Nzc@._V1_SX300.jpg"
            )
        ))
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Movie>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            color = TextWhite,
            modifier = Modifier.padding(15.dp),

        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.spacing_base_2x)),
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(features.size) {
                MovieThumbnail(feature = features[it])
            }
        }
    }
}


@Composable
fun MovieThumbnail(
    feature: Movie
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base))
            .clip(RoundedCornerShape(5.dp))
            .background(Purple500)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            GlideImage(
                imageModel = feature.posterUrl,
                contentScale = ContentScale.FillWidth,
                circularReveal = CircularReveal(duration = 250),
                placeHolder = ImageBitmap.imageResource(R.drawable.movie_placeholder),
                error = ImageBitmap.imageResource(R.drawable.movie_placeholder),
                modifier = Modifier
                    .height(220.dp),
                alignment = Alignment.Center,
            )
            Text(
                text = feature.title,
                style = MaterialTheme.typography.body1,
                lineHeight = 26.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        all = dimensionResource(
                            id = R.dimen.spacing_base_half
                        )
                    )
            )
        }
    }
}