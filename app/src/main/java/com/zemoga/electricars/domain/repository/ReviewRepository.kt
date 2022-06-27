package com.zemoga.electricars.domain.repository

import com.zemoga.electricars.domain.model.review.Review
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    suspend fun getReviews(stationId: String): Flow<Resource<List<Review>>>
}