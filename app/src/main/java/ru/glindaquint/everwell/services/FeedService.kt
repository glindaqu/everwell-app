package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.feed.FeedDto
import ru.glindaquint.everwell.network.services.FeedNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class FeedService
    @Inject
    constructor(
        private val feedNetworkService: FeedNetworkService,
    ) {
        private val _feeds = MutableStateFlow<List<FeedDto>>(emptyList())
        val feeds = _feeds.asStateFlow()

        fun refreshFeeds(
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            feedNetworkService
                .getOwnedByUser()
                .enqueue(
                    object : Callback<DataResponse<List<FeedDto>>> {
                        override fun onResponse(
                            call: Call<DataResponse<List<FeedDto>>>,
                            response: Response<DataResponse<List<FeedDto>>>,
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                _feeds.value = response.body()!!.data!!
                                onSuccess?.invoke()
                            } else {
                                onFailure?.invoke(Throwable("Unknown error received: ${response.message()}"))
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<List<FeedDto>>>,
                            t: Throwable,
                        ) {
                            onFailure?.invoke(t)
                        }
                    },
                )
        }
    }
