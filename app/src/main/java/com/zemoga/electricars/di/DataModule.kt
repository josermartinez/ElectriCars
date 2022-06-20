package com.zemoga.electricars.di

import com.apollographql.apollo3.ApolloClient
import com.zemoga.electricars.data.remote.source.CarRemoteDataSource
import com.zemoga.electricars.data.remote.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    @Singleton
    fun providesApolloClient(authorizationInterceptor: AuthorizationInterceptor): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl("https://api.chargetrip.io/graphql")
            .addHttpInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesCarSource(apolloClient: ApolloClient): CarRemoteDataSource {
        return CarRemoteDataSource(apolloClient)
    }
}