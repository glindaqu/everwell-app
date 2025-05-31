package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.GET
import ru.glindaquint.everwell.network.dto.DataResponse

interface AdviceRepository {
    @GET("/advice/get")
    fun getRandomAdvice(): Call<DataResponse<String>>
}
