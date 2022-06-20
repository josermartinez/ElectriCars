package com.zemoga.electricars.data.remote.source

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.zemoga.electricars.CarListQuery
import com.zemoga.electricars.CarQuery

class CarRemoteDataSource(
    private val apolloClient: ApolloClient
) {
    suspend fun getCard(carId: String): ApolloResponse<CarQuery.Data> {
        return apolloClient.query(CarQuery(carId)).execute()
    }

    suspend fun getCarList(size: Int = 20): ApolloResponse<CarListQuery.Data> {
        return apolloClient.query(CarListQuery(Optional.presentIfNotNull(size))).execute()
    }
}