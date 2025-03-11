package ru.glindaquint.everwell.screens.pressure.sliderDatePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.utils.isSameDay
import java.util.Calendar

@Suppress("ktlint:standard:function-naming")
@Composable
fun SliderDatePickerDay(
    state: SliderDatePickerState,
    onClick: () -> Unit,
) {
    val maxDaysInMonth =
        remember { mutableIntStateOf(0) }

    val lazyRowState = rememberLazyListState()

    LaunchedEffect(state.date.value) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, state.year.intValue)
        calendar.set(Calendar.MONTH, state.month.intValue)
        maxDaysInMonth.intValue = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    LaunchedEffect(Unit) {
        delay(1000)
        lazyRowState.animateScrollToItem(state.day.intValue - 3)
    }

    LazyRow(
        state = lazyRowState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 35.dp),
    ) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, state.year.intValue)
        calendar.set(Calendar.MONTH, state.month.intValue)

        items((1..maxDaysInMonth.intValue).toList()) {
            calendar.set(Calendar.DAY_OF_MONTH, it)
            Box(
                modifier =
                    Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .clickable {
                            state.updateSelectedDate(day = it)
                            onClick()
                        }.then(
                            if (isSameDay(calendar.time, state.selectedDate.value)) {
                                Modifier.background(
                                    color = BloodPressureAccent.copy(0.4f),
                                    shape = CircleShape,
                                )
                            } else {
                                Modifier.background(
                                    color = BloodPressureAccent,
                                    shape = CircleShape,
                                )
                            },
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = it.toString(), fontSize = 16.sp, color = Color.White)
            }
        }
    }
}
