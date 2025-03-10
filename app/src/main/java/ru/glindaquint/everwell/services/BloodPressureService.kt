package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.bloodPressure.AddBloodPressureRequest
import ru.glindaquint.everwell.network.dto.bloodPressure.BloodPressureDto
import ru.glindaquint.everwell.network.dto.bloodPressure.GetUsersBloodPressuresResponse
import ru.glindaquint.everwell.network.services.BloodPressureNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class BloodPressureService
    @Inject
    constructor(
        private val networkService: BloodPressureNetworkService,
    ) {
        private val _bloodPressures = MutableStateFlow(listOf<BloodPressureDto>())
        val bloodPressures = _bloodPressures.asStateFlow()

        init {
            refreshBloodPressures()
        }

        fun addBloodPressure(
            request: AddBloodPressureRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            networkService.add(request).enqueue(
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
            refreshBloodPressures()
        }

        private fun refreshBloodPressures(
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            networkService.getOwnedByUser().enqueue(
                object : Callback<GetUsersBloodPressuresResponse> {
                    override fun onResponse(
                        call: Call<GetUsersBloodPressuresResponse>,
                        response: Response<GetUsersBloodPressuresResponse>,
                    ) {
                        if (response.body() != null) {
                            _bloodPressures.value = response.body()!!.bloodPressures
                            onSuccess?.invoke()
                        } else {
                            onFailure?.invoke(Throwable("Something went wrong"))
                        }
                    }

                    override fun onFailure(
                        call: Call<GetUsersBloodPressuresResponse>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }
    }
