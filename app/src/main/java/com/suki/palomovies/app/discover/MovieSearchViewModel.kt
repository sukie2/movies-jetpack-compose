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

    val moviesList: MutableState<List<Movie>> = mutableStateOf(ArrayList())

    init {
        searchMovie(query = "marvel")
    }

    fun searchMovie(query: String){
        viewModelScope.launch {
            val result = movieRepository.searchMovie(
                query = query,
                type = "movie",
                page = 1,

            )
            moviesList.value = result
        }
    }
}
