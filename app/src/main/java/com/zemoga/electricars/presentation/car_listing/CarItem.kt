package com.zemoga.electricars.presentation.car_listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.zemoga.electricars.domain.model.Car

@Composable
fun CarItem(modifier: Modifier = Modifier, item: Car, onClick: (String) -> Unit = {}) {
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier
        .padding(8.dp)
        .clickable {
            onClick(item.id)
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .aspectRatio(3f / 2f),
                painter = rememberAsyncImagePainter(model = item.media?.image?.thumbnailUrl),
                contentDescription = null
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.naming?.model.orEmpty(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.naming?.make.orEmpty(),
                    fontSize = 14.sp
                )
            }
        }

    }
}