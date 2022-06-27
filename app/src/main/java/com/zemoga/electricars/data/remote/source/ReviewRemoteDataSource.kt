package com.zemoga.electricars.data.remote.source

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.zemoga.electricars.AddReviewMutation
import com.zemoga.electricars.ReviewQuery

class ReviewRemoteDataSource(
    private val apolloClient: ApolloClient
) {
    suspend fun getReviews(stationId: String): ApolloResponse<ReviewQuery.Data> {
        return apolloClient.query(ReviewQuery(stationId)).execute()
    }

    suspend fun addReview(
        stationId: String,
        rating: Int,
        message: String = ""
    ): ApolloResponse<AddReviewMutation.Data> {
        return apolloClient.mutation(
            AddReviewMutation(
                stationId,
                rating,
                Optional.presentIfNotNull(message)
            )
        ).execute()
    }
}