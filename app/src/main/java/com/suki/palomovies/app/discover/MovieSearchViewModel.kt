package com.suki.palomovies.app.discover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    var moviesList: MutableState<List<Movie>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    var noResultFound = mutableStateOf(false)
    var resultsFetching = mutableStateOf(false)

    init {
        searchMovie(query = "disney")
//        query.value = "disn"
    }

    fun searchMovie(query: String) {
        noResultFound.value = false
        if (query.isNotEmpty()) {
            resultsFetching.value = true
            viewModelScope.launch {
                val result = movieRepository.searchMovie(
                    query = query,
                    type = "movie",
                    page = 1,
                )
                if (result.isEmpty()) {
                    noResultFound.value = true
                }
                resultsFetching.value = false
                moviesList.value = result
            }
        } else {
            resetMovieList()
        }
    }

    fun resetMovieList() {
        moviesList.value = ArrayList()
        noResultFound.value = false
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }
}
