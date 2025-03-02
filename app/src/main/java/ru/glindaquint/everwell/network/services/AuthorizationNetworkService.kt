package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.glindaquint.everwell.network.dto.authorization.AuthorizationResponse
import ru.glindaquint.everwell.network.dto.authorization.RestoreRequest
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

    @GET("/email/send-email")
    fun sendEmail(
        @Query("to") to: String,
        @Query("subject") subject: String,
        @Query("body") body: String,
    ): Call<Void>

    @POST("/auth/restore-password")
    fun restorePassword(
        @Body request: RestoreRequest,
    ): Call<Void>
}
