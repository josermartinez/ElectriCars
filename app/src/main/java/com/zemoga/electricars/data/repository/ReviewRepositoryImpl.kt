package com.zemoga.electricars.data.repository

import com.zemoga.electricars.data.remote.source.ReviewRemoteDataSource
import com.zemoga.electricars.domain.model.review.Review
import com.zemoga.electricars.domain.model.review.toReview
import com.zemoga.electricars.domain.repository.ReviewRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewRemoteDataSource: ReviewRemoteDataSource
) : ReviewRepository {

    override suspend fun getReviews(stationId: String): Flow<Resource<List<Review>>> {
        return flow {
            emit(Resource.Loading())
            val response = reviewRemoteDataSource.getReviews(stationId)
            val errors = response.errors
            if (errors.isNullOrEmpty().not()) {
                emit(Resource.Error(message = errors?.first()?.message.orEmpty()))
            } else {
                response.data?.let {
                    val reviews = it.reviewList?.map { reviewList ->
                        reviewList.toReview()
                    }
                    emit(Resource.Success(data = reviews))
                } ?: emit(Resource.Error(message = ""))
            }
        }
    }
}