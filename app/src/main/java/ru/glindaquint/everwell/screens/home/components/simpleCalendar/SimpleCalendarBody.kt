package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SimpleCalendarBody(
    state: SimpleCalendarUiState,
    onItemClick: (Any) -> Unit,
) {
    val calendar = Calendar.getInstance()
    val currentDate = state.getDate().time

    FlowRow(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 9.dp, bottom = 25.dp)
                .padding(horizontal = 16.dp),
        maxItemsInEachRow = 7,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        for (i in state.weekDayTitles) {
            SimpleCalendarBodyItem(value = i, color = MainSecondary, onClick = { onItemClick(it) })
        }
        for (i in state.previousMonthDaysCount - state.firstDayOffset..state.previousMonthDaysCount) {
            SimpleCalendarBodyItem(value = i, color = MainAccent, onClick = { onItemClick(it) })
        }
        for (i in 1..state.currentMonthDaysCount) {
            calendar.set(Calendar.DAY_OF_MONTH, i)
            SimpleCalendarBodyItem(
                value = i,
                color = if (currentDate == calendar.time.time) MainOnBackground else MainSecondary,
                onClick = { onItemClick(it) },
            )
        }
        for (i in 1..state.lastDayOffset) {
            SimpleCalendarBodyItem(value = i, color = MainAccent, onClick = { onItemClick(it) })
        }
    }
}
