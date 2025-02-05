package ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun DailyAdvice() {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(
            text = "Day's advice",
            fontSize = 14.sp,
            color = MainSecondary,
            fontWeight = FontWeight.Medium,
        )
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 112.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        MainSecondary,
                    ),
        ) {
            Text(
                text = "Попей водички, восполни водный баланс в организме и подыши свежим воздухом.",
                modifier = Modifier.padding(start = 12.dp, top = 9.dp),
                fontSize = 14.sp,
            )
        }
    }
}
