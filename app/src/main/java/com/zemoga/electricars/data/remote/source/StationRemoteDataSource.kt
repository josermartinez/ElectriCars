package com.zemoga.electricars.data.remote.source

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.zemoga.electricars.StationListQuery
import com.zemoga.electricars.StationQuery

class StationRemoteDataSource(private val apolloClient: ApolloClient) {

    suspend fun getStationList(size: Int = 20): ApolloResponse<StationListQuery.Data> {
        return apolloClient.query(StationListQuery(Optional.presentIfNotNull(size))).execute()
    }

    suspend fun getStation(id: String): ApolloResponse<StationQuery.Data> {
        return apolloClient.query(StationQuery(id)).execute()
    }
}