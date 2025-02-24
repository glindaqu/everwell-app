package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.authorization.AuthorizationResponse
import ru.glindaquint.everwell.network.dto.authorization.signIn.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest

interface AuthorizationNetworkService {
    @POST("/auth/sign-up")
    fun signUp(
        @Body signUpRequest: SignUpRequest,
    ): Call<AuthorizationResponse>

    @POST("/auth/sign-in")
    fun signIn(
        @Body signInRequest: SignInRequest,
    ): Call<AuthorizationResponse>
}
