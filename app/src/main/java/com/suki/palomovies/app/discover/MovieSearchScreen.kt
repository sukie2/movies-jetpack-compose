package com.suki.palomovies.app.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.suki.palomovies.R
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.ui.theme.Purple500
import com.suki.palomovies.ui.theme.Purple700
import com.suki.palomovies.ui.theme.TextWhite

@ExperimentalFoundationApi
@Composable
fun MovieSearchScreen() {
    val viewModel = hiltViewModel<MovieSearchViewModel>()
    Box(modifier = Modifier.background(Purple700)){
        FeatureSection(features = viewModel.moviesList.value)
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Movie>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextFieldDemo()

        Text(
            text = stringResource(id = R.string.search_results),
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
fun TextFieldDemo() {
    Column(Modifier.padding(16.dp)) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it }
        )
        Text("The textfield has this text: " + textState.value.text)
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