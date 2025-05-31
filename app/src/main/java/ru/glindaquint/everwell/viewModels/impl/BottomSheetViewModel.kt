package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.services.AdviceService
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel
    @Inject
    constructor(
        private val adviceService: AdviceService,
    ) : ViewModel() {
        private val _dailyAdvice = MutableStateFlow("")
        val dailyAdvice = _dailyAdvice.asStateFlow()

        init {
            viewModelScope.launch(Dispatchers.IO) {
                _dailyAdvice.value = adviceService.getAdvice()
            }
        }
    }
