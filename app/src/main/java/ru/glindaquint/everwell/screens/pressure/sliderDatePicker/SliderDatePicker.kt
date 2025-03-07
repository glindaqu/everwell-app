package ru.glindaquint.everwell.screens.pressure.sliderDatePicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary
import java.util.Date

@Suppress("ktlint:standard:function-naming")
@Composable
fun SliderDatePicker(
    state: SliderDatePickerState,
    onDateSelected: (Date) -> Unit,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }
    val brush =
        Brush.linearGradient(
            colors = listOf(BloodPressurePrimary, Color(0xffFF9E81)),
            start =
                Offset(
                    x = viewSize.value.width / 2f,
                    y = viewSize.value.height / 3f,
                ),
            end =
                Offset(
                    x = viewSize.value.width / 2f,
                    y = viewSize.value.height.toFloat(),
                ),
        )

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .drawWithCache {
                    onDrawBehind {
                        drawSliderBackground(
                            brush = brush,
                            topLeftRadius = 40f,
                            topRightRadius = 40f,
                            bottomRightRadius = 160f,
                            bottomLeftRadius = 160f,
                        )
                    }
                }.onGloballyPositioned {
                    viewSize.value = it.size
                },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SliderDatePickerMonth(state = state)
        SliderDatePickerDay(state = state)
    }
}
