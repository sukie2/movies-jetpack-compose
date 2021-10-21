package com.suki.palomovies.app.discover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    var movieDetails: MutableState<MovieDetails> = mutableStateOf(MovieDetails())
    var noResultFound = mutableStateOf(false)
    var isFetching = mutableStateOf(false)

    fun getMovieDetails(movieId: String) {
        noResultFound.value = false
        if (movieId.isNotEmpty()) {
            isFetching.value = true
            viewModelScope.launch {
                val result = movieRepository.getMovieDetails(
                    movieId = movieId,
                    plot = "full"
                )
                if (result.imdbID.isBlank()) {
                    noResultFound.value = true
                }else {
                    movieDetails.value = result
                }
                isFetching.value = false
            }
        } else {
            resetMovieData()
        }
    }

    private fun resetMovieData() {
        movieDetails = mutableStateOf(MovieDetails())
        noResultFound.value = false
    }
}