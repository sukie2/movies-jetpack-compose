package com.suki.palomovies.app.discover

import androidx.compose.runtime.mutableStateOf
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.MovieDetails
import com.suki.palomovies.patform.util.ConnectivityListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var repository: MovieRepository
    private val testDispatcher = TestCoroutineDispatcher()
    private var connectivityManager: ConnectivityListener = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        testDispatcher.runBlockingTest {
            whenever(repository.getMovieDetails(movieId = "test12345","full"))
                .thenReturn(MovieDetails(title = "Dr.Strange", imdbID = "test12345"))
            whenever(connectivityManager.isNetworkAvailable).thenReturn(mutableStateOf(true))
        }
        viewModel = MovieDetailsViewModel(movieRepository = repository, connectivityManager = connectivityManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN movie details fetch yields desired results`() {
        testDispatcher.runBlockingTest {
            viewModel.getMovieDetails(movieId = "test12345")
        }
        assert(viewModel.movieDetails.value == MovieDetails(title = "Dr.Strange", imdbID = "test12345"))
    }
}
