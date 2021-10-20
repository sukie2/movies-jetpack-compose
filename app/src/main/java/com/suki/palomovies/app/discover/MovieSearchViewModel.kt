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

    init {
//        searchMovie(query = "disney")
//        query.value = "disn"
    }

    fun searchMovie(query: String){
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                val result = movieRepository.searchMovie(
                    query = query,
                    type = "movie",
                    page = 1,

                    )
                moviesList.value = result
            }
        } else {
            moviesList.value = ArrayList()
        }
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}
