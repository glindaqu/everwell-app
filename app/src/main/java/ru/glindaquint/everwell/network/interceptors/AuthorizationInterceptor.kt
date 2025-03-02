package ru.glindaquint.everwell.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.glindaquint.everwell.services.JwtService
import javax.inject.Inject

class AuthorizationInterceptor
    @Inject
    constructor(
        private val jwtService: JwtService,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            if (!original.url.toString().contains("auth") &&
                !original.url
                    .toString()
                    .contains("email")
            ) {
                return chain.proceed(
                    original
                        .newBuilder()
                        .header("Authorization", jwtService.token())
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
