package ru.glindaquint.everwell.hilt.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.AuthorizationUiState

@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthorizationModule {
    @Provides
    fun provideSignInUiState(): MutableStateFlow<AuthorizationUiState> = MutableStateFlow(AuthorizationUiState())
}
