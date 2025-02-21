package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.tasks.GetAllTasksResponse
import ru.glindaquint.everwell.network.services.TasksNetworkService
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.services.userService.UserService
import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState
import ru.glindaquint.everwell.viewModels.api.IHomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        preferenceManager: PreferenceManagerImpl,
        val uiState: MutableStateFlow<HomeUiState>,
        private val tasksNetworkService: TasksNetworkService,
        private val userService: Lazy<UserService>,
    ) : ViewModel(),
        IHomeViewModel {
        private val apiToken = "Bearer " + preferenceManager.getString("token")
        val user = userService.get().user

        override fun loadTasks() {
            updateUiState(uiState.value.copy(loading = true))
            tasksNetworkService.getAll(token = apiToken).enqueue(
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
                                    error = null,
                                ),
                            )
                        } else {
                            updateUiState(
                                uiState.value.copy(
                                    error = "Something went wrong",
                                    loading = false,
                                ),
                            )
                        }
                    }

                    override fun onFailure(
                        call: Call<GetAllTasksResponse>,
                        t: Throwable,
                    ) {
                        updateUiState(
                            uiState.value.copy(
                                error = t.message,
                                loading = false,
                            ),
                        )
                    }
                },
            )
        }

        override fun loadUser() {
            updateUiState(
                uiState.value.copy(
                    username =
                        userService
                            .get()
                            .user.value
                            ?.username,
                ),
            )
        }

        override fun updateUiState(state: HomeUiState) {
            this.uiState.value = state
        }
    }
