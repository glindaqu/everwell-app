package ru.glindaquint.everwell.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import ru.glindaquint.everwell.services.jwtService.JwtService
import javax.inject.Inject

class AuthorizationInterceptor
    @Inject
    constructor(
        private val jwtService: JwtService,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            Log.d("", "intercept: ${original.url}")
            if (!original.url.toString().contains("auth")) {
                return chain.proceed(
                    original
                        .newBuilder()
                        .header("Authorization", jwtService.token)
                        .method(original.method, original.body)
                        .build(),
                )
            } else {
                return chain.proceed(
                    original
                        .newBuilder()
                        .method(original.method, original.body)
                        .build(),
                )
            }
        }
    }
