package com.suki.palomovies.app.discover

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.patform.util.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 10

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val connectivityManager: ConnectivityManager,
) : ViewModel() {
    var moviesList: MutableState<List<Movie>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    var noResultFound = mutableStateOf(false)
    var isFetching = mutableStateOf(false)
    var movieListScrollPosition = 0;

    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableStateOf(1)

    fun searchMovie(query: String) {
        if (connectivityManager.isNetworkAvailable.value) {
            noResultFound.value = false
            if (query.isNotEmpty()) {
                isFetching.value = true
                viewModelScope.launch {
                    val result = movieRepository.searchMovie(
                        query = query,
                        type = "movie",
                        page = 1,
                    )
                    if (result.isEmpty()) {
                        noResultFound.value = true
                    } else {
                        moviesList.value = result
                    }
                    isFetching.value = false
                }
            } else {
                resetMovieList()
            }
        }
    }

    fun nextPage(query: String) {
        if (connectivityManager.isNetworkAvailable.value) {
            viewModelScope.launch {
                // prevent duplicate event due to recompose happening to quickly
                if ((movieListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                    isFetching.value = true
                    incrementPage()
                    Log.d(TAG, "nextPage: triggered: ${page.value}")

                    //FIXME: Remove this after demo
                    // just to show pagination, api is fast
                    delay(250)

                    if (page.value > 1) {
                        val result = movieRepository.searchMovie(
                            query = query,
                            type = "movie",
                            page = page.value,
                        )
                        appendMovies(result)
                    }
                    isFetching.value = false
                }
            }
        }
    }

    fun resetMovieList() {
        moviesList.value = ArrayList()
        noResultFound.value = false
        onChangeScrollPosition(0)
        page.value = 1
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onChangeScrollPosition(position: Int){
        movieListScrollPosition = position
    }

    private fun incrementPage(){
        page.value = page.value + 1
    }

    private fun appendMovies(incomingMovies: List<Movie>){
        val current = ArrayList(this.moviesList.value)
        current.addAll(incomingMovies)
        this.moviesList.value = current
    }

}
