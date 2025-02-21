package ru.glindaquint.everwell.services.jwtService

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import javax.inject.Inject

@ActivityRetainedScoped
class JwtService
    @Inject
    constructor(
        preferencesManager: PreferencesManager,
    ) {
        val token = "Bearer " + preferencesManager.getString(PreferencesKeys.NETWORK_TOKEN)
    }
