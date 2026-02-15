package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.tasks.GetAllTasksResponse
import ru.glindaquint.everwell.network.dto.tasks.InsertTaskRequest

interface TasksNetworkService {
    @POST("/tasks/add")
    fun add(
        @Body task: InsertTaskRequest,
    ): Call<Void>

    @GET("/tasks/get-owned-by-user")
    fun getAll(): Call<GetAllTasksResponse>
}
