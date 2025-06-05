package ru.glindaquint.everwell.uiStates.homeUiState

import ru.glindaquint.everwell.network.dto.tasks.TaskDto

data class HomeUiState(
    val username: String? = null,
    val userImage: String? = null,
    val calendar: SimpleCalendarUiState? = null,
    val tasks: List<TaskDto> = listOf(),
    val error: String? = null,
    val loading: Boolean = true,
)
