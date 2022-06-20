package com.zemoga.electricars.data.remote.interceptor

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain

class AuthorizationInterceptor : HttpInterceptor {

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        val requestBuild = request.newBuilder()
            .addHeader("x-client-id", "62ae49dab0d2b6c85e3ac2e3")
            .addHeader("x-app-id", "62ae49dab0d2b6c85e3ac2e5")
            .build()
        return chain.proceed(requestBuild)
    }
}