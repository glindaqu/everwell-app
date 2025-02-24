@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.utils.jwt

import android.util.Base64
import kotlinx.serialization.json.Json
import java.io.UnsupportedEncodingException

object JwtUtils {
    @Throws(Exception::class)
    fun decode(jwt: String?): JwtBody? {
        if (jwt == null) {
            return null
        }
        try {
            val split =
                jwt
                    .split("\\.".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            return Json.decodeFromString(JwtBody.serializer(), getJson(split[1]))
        } catch (e: UnsupportedEncodingException) {
            return null
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, charset("UTF-8"))
    }
}
