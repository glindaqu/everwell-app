package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.services.BloodPressureService
import ru.glindaquint.everwell.uiStates.BloodPressureUiState
import javax.inject.Inject

@HiltViewModel
class BloodPressureViewModel
    @Inject
    constructor(
        private val bloodPressureService: BloodPressureService,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(BloodPressureUiState())
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                bloodPressureService.bloodPressures.collect {
                    _uiState.value = _uiState.value.copy(bloodPressures = it)
                }
            }
        }
    }
