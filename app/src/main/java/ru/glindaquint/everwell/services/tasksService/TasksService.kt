package ru.glindaquint.everwell.services.tasksService

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.tasks.GetAllTasksResponse
import ru.glindaquint.everwell.network.dto.tasks.TaskDto
import ru.glindaquint.everwell.network.services.TasksNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class TasksService
    @Inject
    constructor(
        private val tasksNetworkService: TasksNetworkService,
    ) {
        private val _userTasks: MutableStateFlow<List<TaskDto>> = MutableStateFlow(listOf())
        val userTasks = _userTasks.asStateFlow()

        init {
            refreshTasks()
        }

        fun getSingleTask(taskId: Long): TaskDto? =
            _userTasks.value.find { task ->
                task.taskId == taskId
            }

        private fun refreshTasks(
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            tasksNetworkService.getAll().enqueue(
                object : Callback<GetAllTasksResponse> {
                    override fun onResponse(
                        call: Call<GetAllTasksResponse>,
                        response: Response<GetAllTasksResponse>,
                    ) {
                        if (response.body() != null) {
                            _userTasks.value = response.body()!!.tasks
                            onSuccess?.invoke()
                        }
                    }

                    override fun onFailure(
                        call: Call<GetAllTasksResponse>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }
    }
