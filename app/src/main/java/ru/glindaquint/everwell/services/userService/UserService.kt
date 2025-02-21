package ru.glindaquint.everwell.services.userService

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.services.UserNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class UserService
    @Inject
    constructor(
        private val userNetworkService: UserNetworkService,
    ) {
        val user: MutableStateFlow<GetUserResponse?> = MutableStateFlow(null)

        init {
            refreshUser()
        }

        private fun refreshUser(
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            userNetworkService.getUser().enqueue(
                object : Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>,
                    ) {
                        if (response.body() != null) {
                            user.value = response.body()!!
                            onSuccess?.invoke()
                        }
                    }

                    override fun onFailure(
                        call: Call<GetUserResponse>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }
    }
