package com.suki.palomovies.patform.util

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner

interface ConnectivityListener {
    val isNetworkAvailable: MutableState<Boolean>
    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner)
    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner)
}