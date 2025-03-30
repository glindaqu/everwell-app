package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel
    @Inject
    constructor() : ViewModel() {
        val categories = MutableStateFlow(listOf("Все", "Непрочитанные", "Еда", "Лекарства"))
    }
