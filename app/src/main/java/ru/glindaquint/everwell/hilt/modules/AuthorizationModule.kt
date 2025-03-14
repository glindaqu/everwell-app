package ru.glindaquint.everwell.hilt.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.uiStates.SignUpUiState

@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthorizationModule {
    @Provides
    fun provideSignInUiState(): MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())

    @Provides
    fun provideSignUpUiState(): MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
}
