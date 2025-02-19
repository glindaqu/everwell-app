package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.dto.colors.simpleCalendar.SimpleCalendarBodyHeaderColors
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.ui.theme.Typography

@Suppress("ktlint:standard:function-naming")
@Composable
fun SimpleCalendarBodyHeader(
    title: String,
    colors: SimpleCalendarBodyHeaderColors =
        SimpleCalendarBodyHeaderColors(
            backgroundColor = Color.Transparent,
            foregroundColor = MainSecondary,
        ),
) {
    Box(
        modifier =
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(colors.backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = Typography.bodySmall.fontSize,
            color = colors.foregroundColor,
        )
    }
}
