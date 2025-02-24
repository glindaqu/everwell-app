@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.utils.jwt

import kotlinx.serialization.Serializable

@Serializable
data class JwtBody(
    val role: String,
    val id: Long,
    val email: String,
    val sub: String,
    val iat: Long,
    val exp: Long,
)
