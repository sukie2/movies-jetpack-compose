package com.suki.palomovies.patform.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suki.palomovies.R
import com.suki.palomovies.ui.theme.gothicA1

@Composable
fun ConnectivityMonitor(
  isNetworkAvailable: Boolean,
){
  if(!isNetworkAvailable){
    Column(modifier = Modifier.fillMaxWidth()){
      Text(
        stringResource(id = R.string.no_network),
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(8.dp),
        style = TextStyle(
          color = Color.Magenta,
          fontFamily = gothicA1,
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp)
      )
    }
  }
}