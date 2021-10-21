package com.suki.palomovies.patform.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        lineHeight = 26.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SubHeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun ParagraphText(text: String) {
    Text(
        text = text,
        lineHeight = 20.sp,
        style = MaterialTheme.typography.body2,
        overflow = TextOverflow.Ellipsis,
    )
}