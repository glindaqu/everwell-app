package ru.glindaquint.everwell.screens.pressure.bloodPressureScale

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.utils.pxToDp

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScaleHandle(
    text: String,
    height: Dp,
    position: Dp,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }
    Column(
        modifier =
            Modifier
                .offset(x = position)
                .onGloballyPositioned {
                    viewSize.value = it.size
                }.then(
                    if (position > viewSize.value.width.pxToDp()) {
                        Modifier.offset(x = -viewSize.value.width.pxToDp())
                    } else {
                        Modifier
                    },
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier =
                Modifier
                    .width(5.dp)
                    .height(height)
                    .background(Color.White),
        )
        Text(text = text, fontSize = 12.sp, color = Color.Black)
    }
}
