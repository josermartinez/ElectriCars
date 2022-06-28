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
            .addHeader("x-client-id", "62ba4e6db0d2b6c85e3b9a46")
            .addHeader("x-app-id", "62ba4e6db0d2b6c85e3b9a48")
            .build()
        return chain.proceed(requestBuild)
    }
}