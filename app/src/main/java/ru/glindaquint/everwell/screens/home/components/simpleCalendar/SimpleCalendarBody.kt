package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState

@OptIn(ExperimentalLayoutApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SimpleCalendarBody(
    state: SimpleCalendarUiState,
    onItemClick: (Any) -> Unit,
) {
    FlowRow(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 9.dp, bottom = 25.dp)
                .padding(horizontal = 16.dp),
        maxItemsInEachRow = 7,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        state.weekDayTitles.forEach { title ->
            SimpleCalendarBodyHeader(title = title)
        }
        state.days.forEach { day ->
            SimpleCalendarBodyItem(model = day, onClick = { onItemClick(it) })
        }
    }
}
