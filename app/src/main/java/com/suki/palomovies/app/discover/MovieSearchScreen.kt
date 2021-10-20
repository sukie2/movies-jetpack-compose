package com.suki.palomovies.app.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.suki.palomovies.R
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.ui.theme.Purple500
import com.suki.palomovies.ui.theme.Purple700
import com.suki.palomovies.ui.theme.TextWhite

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MovieSearchScreen() {
    val viewModel = hiltViewModel<MovieSearchViewModel>()
    val movieList = viewModel.moviesList.value
    Box(modifier = Modifier
        .background(Purple700)){
        Column(modifier = Modifier
            .fillMaxSize()) {
            var headerText = stringResource(id = R.string.search_results)
            if (viewModel.moviesList.value.isEmpty()) {
                headerText = stringResource(id = R.string.start_by)
                HeaderView(headerText)
                SearchBar(viewModel = viewModel)
                NoResultView()
            } else {
                HeaderView(headerText)
                SearchBar(viewModel = viewModel)
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.spacing_base_2x)),
                ) {
                    items(movieList.size) {
                        MovieThumbnail(feature = movieList[it])
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderView(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        color = TextWhite,
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.spacing_base_2x))
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_base_2x))
    )
}

@Composable
fun NoResultView() {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {
        Card(
            modifier = Modifier.size(200.dp).wrapContentSize(Alignment.Center),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            Image(
                painterResource(R.drawable.movie_placeholder),
                contentDescription = "EmptyResultsImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(viewModel: MovieSearchViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val query = viewModel.query.value
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen.spacing_base_2x))
        .padding(bottom = 0.dp)) {

        TextField(
            value = query,
            onValueChange = { viewModel.onQueryChanged(it) },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.search))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            textStyle = TextStyle(color = TextWhite),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.searchMovie(query = query)
                    keyboardController?.hide()
                })
        )
    }

}

@Composable
fun MovieThumbnail(
    feature: Movie
) {
    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(dimensionResource(id = R.dimen.spacing_base_half))
            .background(Purple500)
            .padding(dimensionResource(id = R.dimen.spacing_base_half))
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
                requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL),
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