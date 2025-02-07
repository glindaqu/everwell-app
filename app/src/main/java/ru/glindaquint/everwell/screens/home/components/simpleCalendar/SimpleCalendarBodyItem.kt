package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.types.simpleCalendar.SimpleCalendarBodyItemColors
import ru.glindaquint.everwell.types.simpleCalendar.SimpleCalendarBodyItemDto
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.ui.theme.Typography

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SimpleCalendarBodyItem(
    model: SimpleCalendarBodyItemDto,
    colors: SimpleCalendarBodyItemColors =
        SimpleCalendarBodyItemColors(
            currentMonthSelectedBackgroundColor = MainAccent,
            currentMonthSelectedForegroundColor = MainPrimary,
            currentMonthUnselectedBackgroundColor = Color.Transparent,
            currentMonthUnselectedForegroundColor = MainSecondary,
            otherMonthBackgroundColor = Color.Transparent,
            otherMonthForegroundColor = MainBackground.copy(0.9f),
        ),
    onClick: (Any) -> Unit,
) {
    val background =
        when {
            model.selected -> colors.currentMonthSelectedBackgroundColor
            model.relatedWithCurrentMonth -> colors.currentMonthUnselectedBackgroundColor
            else -> colors.otherMonthBackgroundColor
        }
    val foreground =
        when {
            model.selected -> colors.currentMonthSelectedForegroundColor
            model.relatedWithCurrentMonth -> colors.currentMonthUnselectedForegroundColor
            else -> colors.otherMonthForegroundColor
        }

    Box(
        modifier =
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(background)
                .clickable { onClick(model.day) },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = model.day.toString(),
            textAlign = TextAlign.Center,
            fontSize = Typography.bodySmall.fontSize,
            color = foreground,
            fontWeight = if (model.selected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}
