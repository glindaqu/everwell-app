package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SimpleCalendarBodyItem(
    value: Any,
    color: Color,
    onClick: (Any) -> Unit,
) {
    Text(
        text = value.toString(),
        modifier = Modifier.size(20.dp).clip(CircleShape).clickable { onClick(value) },
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        color = color,
    )
}
