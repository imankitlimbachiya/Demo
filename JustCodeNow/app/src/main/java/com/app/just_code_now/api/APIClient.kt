package com.app.just_code_now.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class APIClient {
    class NetInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request().newBuilder()
                .addHeader("Connection", "close").build()
            return chain.proceed(request)
        }
    }
}