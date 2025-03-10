package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.bloodPressure.AddBloodPressureRequest
import ru.glindaquint.everwell.network.dto.bloodPressure.GetUsersBloodPressuresResponse

interface BloodPressureNetworkService {
    @POST("/blood-pressure/add")
    fun add(
        @Body request: AddBloodPressureRequest,
    ): Call<Void>

    @GET("/blood-pressure/get-owned-by-user")
    fun getOwnedByUser(): Call<GetUsersBloodPressuresResponse>
}
