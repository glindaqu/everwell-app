package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SimpleCalendarContainer(content: @Composable ColumnScope.() -> Unit) {
    val calendarSize = remember { mutableStateOf(IntSize(0, 0)) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.pxToDp())
                .clip(RoundedCornerShape(12.dp))
                .background(
                    brush =
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF326779), Color(0xff0C2342)),
                            start =
                                Offset(
                                    x = calendarSize.value.width / 2f,
                                    y = calendarSize.value.height / 6f,
                                ),
                        ),
                ).onGloballyPositioned {
                    calendarSize.value = it.size
                },
    ) {
        content()
    }
}
