package com.suki.palomovies.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import com.suki.palomovies.patform.util.ConnectivityListener
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConnectivityListenerModule::class]
)

@Module
object FakeConnectivityListenerModule {
    @Singleton
    @Provides
    fun provideFakeConnectivityListener() = object : ConnectivityListener {
        override val isNetworkAvailable: MutableState<Boolean>
            get() = mutableStateOf(true)

        override fun registerConnectionObserver(lifecycleOwner: LifecycleOwner) {

        }

        override fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner) {

        }

    }
}

