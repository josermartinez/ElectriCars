package com.zemoga.electricars.presentation.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zemoga.electricars.R
import com.zemoga.electricars.domain.model.review.Review
import com.zemoga.electricars.presentation.common.RatingBar
import com.zemoga.electricars.ui.spacing

@Composable
fun ReviewsSection(modifier: Modifier = Modifier, reviews: List<Review>? = emptyList()) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.reviews_section_title),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Column(modifier = Modifier.fillMaxWidth()) {
                reviews?.forEach { review ->
                    ReviewItemList(review = review)
                }
            }
        }
    }
}

@Composable
fun ReviewItemList(modifier: Modifier = Modifier, review: Review = Review()) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = review.message,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.reviews_section_rating_label),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.small
                )
            )
            RatingBar(rating = review.rating)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
    }
}

@Preview
@Composable
fun ReviewsSectionPreview() {
    ReviewsSection(reviews = listOf(Review(message = "This is a station review", rating = 4)))
}