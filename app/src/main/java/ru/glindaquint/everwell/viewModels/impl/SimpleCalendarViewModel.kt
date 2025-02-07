package ru.glindaquint.everwell.viewModels.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.models.simpleCalendar.SimpleCalendarModel
import ru.glindaquint.everwell.types.simpleCalendar.SimpleCalendarBodyItemDto
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState
import ru.glindaquint.everwell.viewModels.api.ISimpleCalendarViewModel
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

        override fun setDate(date: Date) {
            val days: MutableList<SimpleCalendarBodyItemDto> = mutableListOf()
            simpleCalendarModel.setDate(date)
            for (i in simpleCalendarModel.previousMonthDaysCount - simpleCalendarModel.firstDayOffset..simpleCalendarModel.previousMonthDaysCount) {
                days.add(
                    SimpleCalendarBodyItemDto(
                        day = i,
                        relatedWithCurrentMonth = false,
                        selected = false,
                    ),
                )
            }
            for (i in 1..simpleCalendarModel.currentMonthDaysCount) {
                days.add(
                    SimpleCalendarBodyItemDto(
                        day = i,
                        relatedWithCurrentMonth = true,
                        selected =
                            SimpleCalendarModel.isDatesEqual(
                                simpleCalendarModel.date,
                                SimpleCalendarModel.createDate(
                                    day = i,
                                    month = simpleCalendarModel.currentMonth,
                                    year = simpleCalendarModel.currentYear,
                                ),
                            ),
                    ),
                )
                Log.d(
                    "",
                    "${
                        SimpleCalendarModel.createDate(
                            day = i,
                            month = simpleCalendarModel.currentMonth,
                            year = simpleCalendarModel.currentYear,
                        )
                    }",
                )
            }
            for (i in 1..simpleCalendarModel.lastDayOffset) {
                days.add(
                    SimpleCalendarBodyItemDto(
                        day = i,
                        relatedWithCurrentMonth = false,
                        selected = false,
                    ),
                )
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
