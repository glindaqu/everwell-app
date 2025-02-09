package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.home.TaskDto
import ru.glindaquint.everwell.uiStates.TaskListUiState
import ru.glindaquint.everwell.viewModels.api.ITaskListViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel
    @Inject
    constructor(
        val taskListUiState: MutableStateFlow<TaskListUiState>,
    ) : ViewModel(),
        ITaskListViewModel {
        init {
            updateUiState(
                taskListUiState.value.copy(
                    tasks =
                        listOf(
                            TaskDto(
                                title = "Create a hextech",
                                isCompleted = false,
                                isNotifying = false,
                                isRepeating = true,
                                deadline = 1217381L,
                            ),
                            TaskDto(
                                title = "Save Viktor",
                                isCompleted = true,
                                isNotifying = true,
                                isRepeating = true,
                                deadline = 1217381L,
                            ),
                        ),
                ),
            )
        }

        override fun updateUiState(state: TaskListUiState) {
            taskListUiState.value = state
        }
    }
