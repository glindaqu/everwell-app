package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import javax.inject.Inject

@ActivityRetainedScoped
class JwtService
    @Inject
    constructor(
        private val preferencesManager: PreferencesManager,
    ) {
        fun token(): String = "Bearer " + preferencesManager.getString(PreferencesKeys.NETWORK_TOKEN)
    }
