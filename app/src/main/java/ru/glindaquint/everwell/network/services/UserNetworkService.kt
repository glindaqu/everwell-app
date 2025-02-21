package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.GET
import ru.glindaquint.everwell.network.dto.users.GetUserResponse

interface UserNetworkService {
    @GET("/users/get-user")
    fun getUser(): Call<GetUserResponse>
}
