package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.authorization.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.SignInResponse
import ru.glindaquint.everwell.network.dto.authorization.UserDto

interface AuthorizationService {
    @POST("/auth/sign-up")
    fun signUp(
        @Body user: UserDto,
    ): Call<Void>

    @POST("/auth/sign-in")
    fun signIn(
        @Body signInRequest: SignInRequest,
    ): Call<SignInResponse>
}
