package com.suki.palomovies.di

import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.data.Movie
import com.suki.palomovies.patform.repository.data.MovieDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieModule::class]
)

@Module
object FakeInventoryRepositoryModule {
    @Singleton
    @Provides
    fun provideFakePricingRepository() = object : MovieRepository {
        override suspend fun searchMovie(query: String, type: String, page: Int): List<Movie> {
            return listOf()
        }

        override suspend fun getMovieDetails(movieId: String, plot: String): MovieDetails {
            return MovieDetails()
        }
    }
}

