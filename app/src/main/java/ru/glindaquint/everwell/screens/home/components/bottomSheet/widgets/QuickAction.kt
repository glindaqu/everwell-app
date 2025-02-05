package ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun QuickAction(
    painter: Painter? = null,
    title: String,
) {
    Box(contentAlignment = Alignment.BottomStart) {
        Box(
            modifier =
                Modifier
                    .size(85.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray),
        )
        if (painter != null) {
            Icon(
                painter = painter,
                contentDescription = "Quick action $title",
                modifier = Modifier.size(85.dp),
            )
        }
        Text(
            text = title,
            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
            color = MainSecondary,
            fontSize = 12.sp,
        )
    }
}
