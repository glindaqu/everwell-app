package ru.glindaquint.everwell.services.preferencesManager

interface PreferencesManager {
    fun saveString(
        key: String,
        value: String,
    )

    fun getString(
        key: String,
        defaultValue: String = "",
    ): String

    fun setPreferencesChangeListener(callback: (String?) -> Unit)

    fun unsetPreferencesChangeListener()
}
