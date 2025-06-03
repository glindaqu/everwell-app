package ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun QuickAction(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    title: String,
    onClick: () -> Unit,
) {
    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.clickable { onClick() }) {
        if (painter != null) {
            Image(
                painter = painter,
                contentDescription = "Quick action $title",
                modifier =
                    Modifier
                        .size(85.dp)
                        .clip(RoundedCornerShape(12.dp)),
            )
        } else {
            Box(
                modifier =
                    modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray),
            )
        }
        Text(
            text = title,
            softWrap = true,
            modifier =
                Modifier
                    .padding(start = 5.dp, bottom = 5.dp)
                    .width(75.dp),
            color = MainSecondary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 12.sp,
        )
    }
}
