package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.authorization.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.SignInResponse
import ru.glindaquint.everwell.network.dto.authorization.UserDto

interface UsersService {
    @POST("/auth/signup")
    fun signUp(
        @Body user: UserDto,
    ): Call<Void>

    @POST("/auth/signin")
    fun signIn(
        @Body signInRequest: SignInRequest,
    ): Call<SignInResponse>
}
