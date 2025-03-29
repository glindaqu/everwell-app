package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.dto.objective.simpleCalendar.SimpleCalendarBodyItemDto
import ru.glindaquint.everwell.models.simpleCalendar.SimpleCalendarModel
import ru.glindaquint.everwell.uiStates.homeUiState.SimpleCalendarUiState
import ru.glindaquint.everwell.utils.isSameDay
import ru.glindaquint.everwell.viewModels.api.ISimpleCalendarViewModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SimpleCalendarViewModel
    @Inject
    constructor(
        val simpleCalendarUiState: MutableStateFlow<SimpleCalendarUiState>,
        private val simpleCalendarModel: SimpleCalendarModel,
    ) : ViewModel(),
        ISimpleCalendarViewModel {
        init {
            setDate()
        }

        fun setDate(date: Date = Date()) {
            simpleCalendarModel.setDate(date)
            val days: MutableList<SimpleCalendarBodyItemDto> = mutableListOf()
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.set(Calendar.DAY_OF_MONTH, -simpleCalendarModel.firstDayOffset)
            val totalDaysCount =
                simpleCalendarModel.firstDayOffset + simpleCalendarModel.currentMonthDaysCount + simpleCalendarModel.lastDayOffset
            for (i in 0..totalDaysCount) {
                days.add(
                    SimpleCalendarBodyItemDto(
                        day = calendar.get(Calendar.DAY_OF_MONTH),
                        relatedWithCurrentMonth =
                            simpleCalendarModel.currentMonth ==
                                calendar.get(Calendar.MONTH),
                        selected =
                            isSameDay(
                                simpleCalendarModel.date,
                                calendar.time,
                            ),
                    ),
                )
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
            }
            updateUiState(
                SimpleCalendarUiState(
                    currentMonthDay = simpleCalendarModel.day(),
                    currentMonth = simpleCalendarModel.month(),
                    currentYear = simpleCalendarModel.year(),
                    days = days,
                ),
            )
        }

        override fun updateUiState(state: SimpleCalendarUiState) {
            simpleCalendarUiState.value = state
        }
    }
