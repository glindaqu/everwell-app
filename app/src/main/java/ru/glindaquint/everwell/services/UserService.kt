package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.authorization.AuthorizationResponse
import ru.glindaquint.everwell.network.dto.authorization.RestoreRequest
import ru.glindaquint.everwell.network.dto.authorization.signIn.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest
import ru.glindaquint.everwell.network.services.AuthorizationNetworkService
import ru.glindaquint.everwell.network.services.UserNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class UserService
    @Inject
    constructor(
        private val userNetworkService: UserNetworkService,
        private val authorizationNetworkService: AuthorizationNetworkService,
    ) {
        private val _user: MutableStateFlow<GetUserResponse?> = MutableStateFlow(null)
        val user = _user.asStateFlow()

        fun signIn(
            request: SignInRequest,
            onSuccess: ((String) -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            authorizationNetworkService.signIn(request).enqueue(
                object : Callback<AuthorizationResponse> {
                    override fun onResponse(
                        call: Call<AuthorizationResponse>,
                        response: Response<AuthorizationResponse>,
                    ) {
                        if (response.body() != null) {
                            onSuccess?.invoke(response.body()!!.token)
                            refreshUser()
                        } else {
                            onFailure?.invoke(Throwable("Something went wrong"))
                        }
                    }

                    override fun onFailure(
                        call: Call<AuthorizationResponse>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }

        fun signUp(
            request: SignUpRequest,
            onSuccess: ((String) -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            authorizationNetworkService.signUp(request).enqueue(
                object : Callback<AuthorizationResponse> {
                    override fun onResponse(
                        call: Call<AuthorizationResponse>,
                        response: Response<AuthorizationResponse>,
                    ) {
                        if (response.body() != null) {
                            onSuccess?.invoke(response.body()!!.token)
                        } else {
                            onFailure?.invoke(Throwable("Something went wrong"))
                        }
                    }

                    override fun onFailure(
                        call: Call<AuthorizationResponse>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }

        fun refreshUser(
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
                            _user.value = response.body()!!
                            onSuccess?.invoke()
                        } else {
                            onFailure?.invoke(Throwable("Something went wrong"))
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

        fun sendVerificationCode(
            email: String,
            code: String,
        ) {
            authorizationNetworkService
                .sendEmail(
                    to = email,
                    subject = "Everwell",
                    body = "Hello! Your verification code for Everwell: $code",
                ).enqueue(
                    object : Callback<Void> {
                        override fun onResponse(
                            call: Call<Void>,
                            response: Response<Void>,
                        ) {
                        }

                        override fun onFailure(
                            call: Call<Void>,
                            t: Throwable,
                        ) {
                        }
                    },
                )
        }

        fun restorePassword(
            request: RestoreRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            authorizationNetworkService.restorePassword(request).enqueue(
                object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>,
                    ) {
                        onSuccess?.invoke()
                    }

                    override fun onFailure(
                        call: Call<Void>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }

        fun updateProfile(request: UpdateProfileRequest) {
            userNetworkService.updateProfile(request).enqueue(
                object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>,
                    ) {
                        refreshUser()
                    }

                    override fun onFailure(
                        call: Call<Void>,
                        t: Throwable,
                    ) {
                    }
                },
            )
        }
    }
