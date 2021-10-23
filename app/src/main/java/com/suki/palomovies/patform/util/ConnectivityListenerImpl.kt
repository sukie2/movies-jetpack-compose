package com.suki.palomovies.patform.util

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityListenerImpl @Inject constructor(
  context: Context,
): ConnectivityListener {
  private val connectionLiveData = ConnectionLiveData(context)

  private val _isNetworkAvailable = mutableStateOf(true)

  override val isNetworkAvailable: MutableState<Boolean>
    get() = _isNetworkAvailable

  override fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.observe(lifecycleOwner, { isConnected ->
      isConnected?.let { isNetworkAvailable.value = it }
    })
  }

  override fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.removeObservers(lifecycleOwner)
  }
}