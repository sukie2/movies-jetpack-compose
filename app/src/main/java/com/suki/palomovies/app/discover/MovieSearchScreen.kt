package com.suki.palomovies.app.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
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
fun MovieSearchScreen(navController: NavController) {
    val viewModel = hiltViewModel<MovieSearchViewModel>()
    val movieList = viewModel.moviesList.value
    val page = viewModel.page.value
    Box(
        modifier = Modifier
            .background(Purple700)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var headerText = stringResource(id = R.string.search_results)
            if (viewModel.moviesList.value.isEmpty()) {
                headerText = stringResource(id = R.string.start_by)
                HeaderView(viewModel = viewModel, title = headerText)
                SearchBar(viewModel = viewModel)
                NoResultView(viewModel = viewModel)
            } else {
                HeaderView(viewModel = viewModel, title = headerText)
                SearchBar(viewModel = viewModel)
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(id = R.dimen.spacing_base_2x)),
                ) {
                    itemsIndexed(
                        items = movieList
                    ) { index, movie ->
                        viewModel.onChangeScrollPosition(index)
                        if((index + 1) >= (page * PAGE_SIZE) && !viewModel.isFetching.value){
                            viewModel.nextPage(viewModel.query.value)
                        }
                        MovieThumbnail(feature = movie, onClick = {
                            navController.navigate("movie_details")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderView(viewModel: MovieSearchViewModel, title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            color = TextWhite,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.spacing_base_2x))
        )
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
fun NoResultView(viewModel: MovieSearchViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .size(200.dp),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            Image(
                painterResource(R.drawable.movie_placeholder),
                contentDescription = "EmptyResultsImage",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_base_2x)))
        if (viewModel.noResultFound.value) {
            Text(
                stringResource(id = R.string.no_results_found),
                style = MaterialTheme.typography.h2,
                color = TextWhite,
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(viewModel: MovieSearchViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val query = viewModel.query.value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_base_2x))
            .padding(bottom = 0.dp)
    ) {

        TextField(
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.spacing_base_2x))
                        .size(
                            dimensionResource(id = R.dimen.spacing_base_3x)
                        ),
                )
            },
            trailingIcon = {
                if (query != "") {
                    IconButton(
                        onClick = {
                            viewModel.query.value = ""
                            viewModel.resetMovieList()
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
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
                }),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                leadingIconColor = Color.White,
                trailingIconColor = Color.White,
            )
        )
    }
}

@Composable
fun MovieThumbnail(
    feature: Movie,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.spacing_base_half))
            .background(Purple500)
            .clip(RoundedCornerShape(10.dp))
            .padding(dimensionResource(id = R.dimen.spacing_base_half))
            .clickable(onClick = onClick),
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