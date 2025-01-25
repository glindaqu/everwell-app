@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.network.dto.authorization

data class SignInResponse(
    val token: String = "",
)
