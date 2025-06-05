package ru.glindaquint.everwell.network.services

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest

interface UserNetworkService {
    @GET("/users/get-user")
    fun getUser(): Call<GetUserResponse>

    @POST("/users/update-profile")
    fun updateProfile(
        @Body request: UpdateProfileRequest,
    ): Call<Void>

    @Multipart
    @POST("/users/update-profile-image")
    fun updateProfileImage(
        @Part image: MultipartBody.Part,
    ): Call<Void>
}
