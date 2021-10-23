package com.suki.palomovies.di

import android.content.Context
import com.suki.palomovies.patform.util.ConnectivityListener
import com.suki.palomovies.patform.util.ConnectivityListenerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityListenerModule {
    @DelicateCoroutinesApi
    @Singleton
    @Provides
    fun provideConnectionListener(@ApplicationContext appContext: Context): ConnectivityListener =
        ConnectivityListenerImpl(appContext)
}
