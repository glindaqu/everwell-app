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
import ru.glindaquint.everwell.services.userService.UserService
import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState
import ru.glindaquint.everwell.viewModels.api.IHomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val tasksNetworkService: TasksNetworkService,
        userService: Lazy<UserService>,
    ) : ViewModel(),
        IHomeViewModel {
        val user = userService.get().user
        val uiState = MutableStateFlow(HomeUiState(username = user.value?.username))

        override fun loadTasks() {
            updateUiState(uiState.value.copy(loading = true))
            tasksNetworkService.getAll().enqueue(
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

        override fun updateUiState(state: HomeUiState) {
            this.uiState.value = state
        }
    }
