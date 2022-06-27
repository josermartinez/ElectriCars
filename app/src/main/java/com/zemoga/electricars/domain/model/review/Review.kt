package com.zemoga.electricars.domain.model.review

import com.zemoga.electricars.ReviewQuery
import com.zemoga.electricars.util.orZero

data class Review(
    val id: String = "",
    val message: String = "",
    val rating: Int = 0
)

fun ReviewQuery.ReviewList.toReview() = Review(
    id = this.id,
    message = this.message.orEmpty(),
    rating = this.rating.orZero()
)
