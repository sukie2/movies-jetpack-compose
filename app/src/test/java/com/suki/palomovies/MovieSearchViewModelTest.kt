package com.suki.palomovies

import androidx.compose.runtime.mutableStateOf
import com.suki.palomovies.app.discover.MovieSearchViewModel
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.patform.util.ConnectivityListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest {

    private lateinit var viewModel: MovieSearchViewModel
    private lateinit var repository: MovieRepository
    private val testDispatcher = TestCoroutineDispatcher()
    private var connectivityManager: ConnectivityListener = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    //FIXME: In progress

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        testDispatcher.runBlockingTest {
            whenever(repository.searchMovie(query = "Dr.Strange", type = "movie", page = 1))
                .thenReturn(listOf(Movie(movieId = "ff324", title = "Dr.Strange")))
            whenever(connectivityManager.isNetworkAvailable).thenReturn(mutableStateOf(true))
        }
        viewModel = MovieSearchViewModel(movieRepository = repository, connectivityManager = connectivityManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN movie search yields desired results`() {
        testDispatcher.runBlockingTest {
            viewModel.searchMovie(query = "Dr.Strange")
        }
        assert(viewModel.moviesList.value == listOf(Movie(movieId = "ff324", title = "Dr.Strange")))
    }
}
