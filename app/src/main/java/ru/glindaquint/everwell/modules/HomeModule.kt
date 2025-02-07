package ru.glindaquint.everwell.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {
    @Provides
    fun provideSimpleCalendarUiState(): MutableStateFlow<SimpleCalendarUiState> = MutableStateFlow(SimpleCalendarUiState())
}
