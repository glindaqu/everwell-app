package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.network.dto.users.User

data class FamilyGroupUiState(
    val members: Set<User> = setOf<User>(),
    val inviteLink: String? = null,
)
