package ru.glindaquint.everwell.network.interceptors

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

            val request =
                original
                    .newBuilder()
                    .header("Authorization", jwtService.token)
                    .method(original.method, original.body)
                    .build()

            return chain.proceed(request)
        }
    }
