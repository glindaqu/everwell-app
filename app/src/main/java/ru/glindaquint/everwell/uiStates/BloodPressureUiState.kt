package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.network.dto.bloodPressure.BloodPressureDto

data class BloodPressureUiState(
    val bloodPressures: List<BloodPressureDto> = listOf(),
    val error: String? = null,
    val loading: Boolean = false,
    val measurementsCount: Int = 0,
)
