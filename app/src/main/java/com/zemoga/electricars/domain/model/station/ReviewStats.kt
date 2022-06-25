package com.zemoga.electricars.domain.model.station

import com.zemoga.electricars.fragment.ReviewFragment
import com.zemoga.electricars.util.orZero

data class ReviewStats(
    val rating: Double,
    val count: Int
)

fun ReviewFragment.toReviewStats() = ReviewStats(
    rating = this.rating.orZero(),
    count = this.count.orZero()
)