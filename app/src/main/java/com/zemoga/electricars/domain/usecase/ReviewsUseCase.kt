package com.zemoga.electricars.domain.usecase

import com.zemoga.electricars.domain.model.review.Review
import com.zemoga.electricars.domain.repository.ReviewRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

class ReviewsUseCase(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(stationId: String): Flow<Resource<List<Review>>> {
        return reviewRepository.getReviews(stationId)
    }
}