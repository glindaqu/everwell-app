package ru.glindaquint.everwell.network

import com.google.gson.GsonBuilder
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
            val service =
                Retrofit
                    .Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(api)
            return service
        }
    }
}
