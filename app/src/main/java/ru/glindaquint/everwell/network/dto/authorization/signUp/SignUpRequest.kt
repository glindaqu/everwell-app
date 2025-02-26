package ru.glindaquint.everwell.network.dto.authorization.signUp

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
) : Parcelable {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(json: String?): SignUpRequest = Gson().fromJson(json, SignUpRequest::class.java)
    }
}
