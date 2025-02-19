package ru.glindaquint.everwell.viewModels.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.tasks.GetAllTasksResponse
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.services.TasksService
import ru.glindaquint.everwell.network.services.UserService
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        val uiState: MutableStateFlow<HomeUiState>,
        private val preferenceManager: PreferenceManagerImpl,
        private val tasksService: TasksService,
        private val userService: UserService,
    ) : ViewModel() {
        private val apiToken = "Bearer " + preferenceManager.getString("token")

        fun loadTasks() {
            tasksService.getAll(token = apiToken).enqueue(
                object : Callback<GetAllTasksResponse> {
                    override fun onResponse(
                        call: Call<GetAllTasksResponse>,
                        response: Response<GetAllTasksResponse>,
                    ) {
                        if (response.body() != null) {
                            updateUiState(
                                uiState.value.copy(
                                    tasks = response.body()!!.tasks,
                                    loading = false,
                                ),
                            )
                        } else {
                            Log.d(
                                "NETWORK ERROR",
                                "onResponse: Unknown error, ${response.code()}, $response, ${
                                    preferenceManager.getString(
                                        "token",
                                    )
                                }",
                            )
                        }
                    }

                    override fun onFailure(
                        call: Call<GetAllTasksResponse>,
                        t: Throwable,
                    ) {
                        Log.d("NETWORK ERROR", "onFailure: ${t.message}")
                    }
                },
            )
        }

        fun loadUser() {
            userService.getUser(token = apiToken).enqueue(
                object : Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>,
                    ) {
                        if (response.body() != null) {
                            updateUiState(uiState.value.copy(username = response.body()!!.username))
                        }
                    }

                    override fun onFailure(
                        call: Call<GetUserResponse>,
                        t: Throwable,
                    ) {
                        Log.d("NETWORK ERROR", "onFailure: ${t.message}")
                    }
                },
            )
        }

        fun updateUiState(state: HomeUiState) {
            this.uiState.value = state
        }
    }
