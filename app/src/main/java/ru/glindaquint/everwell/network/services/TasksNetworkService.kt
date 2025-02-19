package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.tasks.GetAllTasksResponse

interface TasksNetworkService {
    @POST("/tasks/add")
    fun add()

    @GET("/tasks/get-owned-by-user")
    fun getAll(
        @Header("Authorization") token: String,
    ): Call<GetAllTasksResponse>
}
