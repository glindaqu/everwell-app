package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.feed.FeedDto
import ru.glindaquint.everwell.network.dto.feed.InsertFeedRequest

interface FeedNetworkService {
    @POST("/feed/add")
    fun insertFeed(
        @Body request: InsertFeedRequest,
    ): Call<Void>

    @GET("/feed/get-owned-by-user")
    fun getOwnedByUser(): Call<DataResponse<List<FeedDto>>>
}
