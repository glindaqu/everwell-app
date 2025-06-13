package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.users.User

interface FamilyGroupService {
    @GET("/family-group/members")
    fun getMembers(): Call<DataResponse<Set<User>>>

    @GET("/family-group/leave")
    fun leave(): Call<Void>

    @GET("/family-group/invite")
    fun getInviteLink(): Call<DataResponse<String>>

    @GET("/family-group/join")
    fun join(
        @Query("familyGroupId") familyGroupId: Long,
    ): Call<Void>
}
