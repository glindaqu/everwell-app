package ru.glindaquint.everwell.network.dto.bloodPressure

import java.util.Date

data class BloodPressureDto(
    val bloodPressureId: Long,
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val heartRate: Int,
    val measurementDateTime: Date,
    val ownerId: Long,
)
