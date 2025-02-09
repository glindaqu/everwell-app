package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.network.dto.home.TaskDto

data class TaskListUiState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val tasks: List<TaskDto> = listOf(),
)
