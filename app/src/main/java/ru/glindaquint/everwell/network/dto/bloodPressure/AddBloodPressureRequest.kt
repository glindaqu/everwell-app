package ru.glindaquint.everwell.network.dto.bloodPressure

data class AddBloodPressureRequest(
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val heartRate: Int,
)
