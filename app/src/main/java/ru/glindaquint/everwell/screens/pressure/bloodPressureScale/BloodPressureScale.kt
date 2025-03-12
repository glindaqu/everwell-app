package ru.glindaquint.everwell.screens.pressure.bloodPressureScale

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScale(
    systolic: Int,
    diastolic: Int,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }
    val brush =
        Brush.linearGradient(
            colors =
                listOf(
                    Color(0xffEED2CC),
                    Color(0xffFCBCB8),
                    Color(0xffE78F8E),
                    Color(0xffF17069),
                    Color(0xffDA5552),
                ),
        )

    val percents =
        if (systolic * diastolic < 3500) 0.05f else (systolic * diastolic - 3500) / 13800f
    val handlePosition = viewSize.value.width.pxToDp() * percents

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .height(42.dp)
                    .fillMaxWidth()
                    .background(
                        brush = brush,
                        shape = CircleShape,
                    ).border(
                        width = 1.5.dp,
                        color = Color.White,
                        shape = CircleShape,
                    ).onGloballyPositioned {
                        viewSize.value = it.size
                    },
        )
        BloodPressureScaleHandle(
            text = "$systolic/$diastolic",
            height = viewSize.value.height.pxToDp(),
            position = handlePosition,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview(backgroundColor = 0xffEED2CC)
fun Scale_Test() {
    BloodPressureScale(119, 46)
}
