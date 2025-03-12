package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.network.dto.bloodPressure.AddBloodPressureRequest
import ru.glindaquint.everwell.services.BloodPressureService
import ru.glindaquint.everwell.uiStates.BloodPressureUiState
import ru.glindaquint.everwell.utils.isSameDay
import ru.glindaquint.everwell.utils.isSameMonth
import java.util.Date
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
                    updateUiState(
                        uiState.value.copy(
                            bloodPressures =
                                it.filter { record ->
                                    isSameDay(record.measurementDateTime, Date())
                                },
                            measurementsCount =
                                it
                                    .filter { record ->
                                        isSameMonth(record.measurementDateTime, Date())
                                    }.size,
                        ),
                    )
                }
            }
        }

        fun filterBloodPressures(date: Date) {
            updateUiState(
                uiState.value.copy(
                    bloodPressures =
                        bloodPressureService.bloodPressures.value.filter { record ->
                            isSameDay(record.measurementDateTime, date)
                        },
                    measurementsCount =
                        bloodPressureService.bloodPressures.value
                            .filter { record ->
                                isSameMonth(record.measurementDateTime, Date())
                            }.size,
                ),
            )
        }

        fun addBloodPressure(request: AddBloodPressureRequest) {
            bloodPressureService.addBloodPressure(
                request = request,
                onSuccess = {
                    updateUiState(
                        _uiState.value.copy(
                            error = null,
                            loading = false,
                        ),
                    )
                },
                onFailure = { t ->
                    updateUiState(
                        _uiState.value.copy(
                            error = t.message,
                            loading = false,
                        ),
                    )
                },
            )
        }

        private fun updateUiState(state: BloodPressureUiState) {
            _uiState.value = state
        }
    }
