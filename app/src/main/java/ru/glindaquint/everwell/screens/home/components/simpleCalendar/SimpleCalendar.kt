package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.models.simpleCalendar.SimpleCalendarModel
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
fun SimpleCalendar() {
    val viewModel = hiltViewModel<SimpleCalendarViewModel>()
    val uiState = viewModel.simpleCalendarUiState.collectAsState()

    SimpleCalendarContainer {
        SimpleCalendarHeader(
            state = uiState.value,
            nextMonthClick = {
                viewModel.setDate(
                    SimpleCalendarModel.addToDate(
                        uiState.value.getDate(),
                        month = 1,
                    ),
                )
            },
            prevMonthClick = {
                viewModel.setDate(
                    SimpleCalendarModel.addToDate(
                        uiState.value.getDate(),
                        month = -1,
                    ),
                )
            },
        )
        SimpleCalendarBody(state = uiState.value, onItemClick = { })
    }
}
