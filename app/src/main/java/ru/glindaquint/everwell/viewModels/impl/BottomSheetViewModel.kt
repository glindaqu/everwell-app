package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.BottomSheetUiState
import ru.glindaquint.everwell.uiStates.WidgetStats
import ru.glindaquint.everwell.viewModels.api.IBottomSheetViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel
    @Inject
    constructor(
        val bottomSheetUiState: MutableStateFlow<BottomSheetUiState>,
    ) : ViewModel(),
        IBottomSheetViewModel {
        init {
            updateUiState(
                bottomSheetUiState.value.copy(
                    dailyAdvice = "Попей водички, восполни водный баланс в организме и подыши свежим воздухом.",
                    widgetStats =
                        WidgetStats(
                            steps = 16000,
                            calories = 800,
                            water = 1,
                        ),
                ),
            )
        }

        override fun updateUiState(state: BottomSheetUiState) {
            bottomSheetUiState.value = state
        }
    }
