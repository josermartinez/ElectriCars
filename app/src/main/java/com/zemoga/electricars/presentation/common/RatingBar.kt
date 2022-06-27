package com.zemoga.electricars.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.zemoga.electricars.ui.spacing

@Composable
fun RatingBar(modifier: Modifier = Modifier, rating: Int) {
    Row(modifier = modifier) {
        for (i in 1..rating) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
        }
    }
}

@Preview
@Composable
fun RatingBarReview() {
    RatingBar(rating = 4)
}