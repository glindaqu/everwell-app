package ru.glindaquint.everwell.network

import com.google.gson.GsonBuilder
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.glindaquint.everwell.network.interceptors.AuthorizationInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityRetainedScoped
class RetrofitFactory
    @Inject
    constructor(
        private val authorizationInterceptor: AuthorizationInterceptor,
    ) {
        companion object {
            private var retrofit: Retrofit? = null
            private var client: OkHttpClient? = null

            // 192.168.1.143
            // 10.0.2.2:8080
            private const val API_BASE_URL = "http://192.168.1.143:8080/"
        }

        fun getInstance(apiUrl: String = API_BASE_URL): Retrofit {
            if (retrofit == null) {
                retrofit =
                    Retrofit
                        .Builder()
                        .baseUrl(apiUrl)
                        .client(getClient())
                        .addConverterFactory(
                            GsonConverterFactory.create(
                                GsonBuilder().setLenient().create(),
                            ),
                        ).build()
            }
            return retrofit!!
        }

        private fun getClient(): OkHttpClient {
            if (client == null) {
                client =
                    OkHttpClient
                        .Builder()
                        .readTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .addInterceptor(authorizationInterceptor)
                        .build()
            }
            return client!!
        }
    }
