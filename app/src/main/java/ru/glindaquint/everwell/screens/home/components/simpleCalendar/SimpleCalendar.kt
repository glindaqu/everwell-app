package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.viewModels.impl.SimpleCalendarViewModel

/**
 * Простой виджет-календарь
 *
 * @see SimpleCalendarViewModel
 *
 * @author anesthesia, glindaqu
 */
@SuppressLint("SimpleDateFormat")
@Suppress("ktlint:standard:function-naming")
@Composable
fun SimpleCalendar(viewModel: SimpleCalendarViewModel = hiltViewModel<SimpleCalendarViewModel>()) {
    val uiState = viewModel.simpleCalendarUiState.collectAsState()

    SimpleCalendarContainer {
        SimpleCalendarHeader(
            state = uiState.value,
            nextMonthClick = { viewModel.setDate(uiState.value.addDate(month = 1)) },
            prevMonthClick = { viewModel.setDate(uiState.value.addDate(month = -1)) },
        )
        SimpleCalendarBody(state = uiState.value, onItemClick = { })
    }
}
