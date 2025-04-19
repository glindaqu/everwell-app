package ru.glindaquint.everwell.network.dto

import java.util.Date

data class DataResponse<T>(
    val data: T? = null, val meta: Meta? = null, val error: Error? = null
)

data class Meta(
    val timestamp: Date, val version: String
)
