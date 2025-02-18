package ru.glindaquint.everwell.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    companion object {
        private const val API_BASE_URL = "http://10.0.2.2:8080/"

        fun <T> build(
            api: Class<T>,
            converterFactory: GsonConverterFactory =
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create(),
                ),
        ): T {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

            // Add Interceptor to HttpClient
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val service =
                Retrofit
                    .Builder()
                    .baseUrl(API_BASE_URL)
                    .client(client) // Set HttpClient to be used by Retrofit
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(api)
            return service
        }
    }
}
