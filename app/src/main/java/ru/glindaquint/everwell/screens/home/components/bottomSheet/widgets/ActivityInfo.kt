package ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun ActivityInfo(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
    value: String,
) {
    val infoWidgetSize = remember { mutableStateOf(IntSize(0, 0)) }
    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF326779), Color(0xff0C2342)),
                        start =
                            Offset(
                                x = infoWidgetSize.value.width / 2f,
                                y = 0f,
                            ),
                    ),
                ).onGloballyPositioned {
                    infoWidgetSize.value = it.size
                },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = MainSecondary,
            )
            Icon(
                painter = painter,
                contentDescription = "Info about $title",
                modifier = Modifier.size((infoWidgetSize.value.width / 2.5).toInt().pxToDp()),
                tint = MainSecondary,
            )
            Text(text = value, color = MainAccent, fontSize = 20.sp)
        }
    }
}
