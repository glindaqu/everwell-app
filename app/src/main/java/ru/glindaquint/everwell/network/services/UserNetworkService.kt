package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest

interface UserNetworkService {
    @GET("/users/get-user")
    fun getUser(): Call<GetUserResponse>

    @POST("/users/update-profile")
    fun updateProfile(
        @Body request: UpdateProfileRequest,
    ): Call<Void>
}
