package ru.glindaquint.everwell.services.preferencesManager

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManagerImpl
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) : PreferencesManager {
        private var listener: SharedPreferences.OnSharedPreferenceChangeListener? = null

        override fun saveString(
            key: String,
            value: String,
        ) {
            sharedPreferences.edit().putString(key, value).apply()
        }

        override fun getString(
            key: String,
            defaultValue: String?,
        ): String? = sharedPreferences.getString(key, defaultValue) ?: defaultValue

        override fun setPreferencesChangeListener(callback: (String?) -> Unit) {
            listener =
                SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                    callback(sharedPreferences.getString(key, null))
                }
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun unsetPreferencesChangeListener() {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
