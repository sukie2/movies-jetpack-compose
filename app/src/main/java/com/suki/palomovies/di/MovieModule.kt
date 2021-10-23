package com.suki.palomovies.di

import com.suki.palomovies.patform.api.MovieApi
import com.suki.palomovies.patform.repository.MovieRepository
import com.suki.palomovies.patform.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Singleton
    @Provides
    fun provideMovieRepository(bpiApi: MovieApi): MovieRepository {
        return MovieRepositoryImpl(bpiApi)
    }
}