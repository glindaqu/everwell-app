package ru.glindaquint.everwell.screens.feed.nutritionDashboard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.ui.theme.FeedSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.NutritionCard(
    value: Int,
    label: String,
    alignment: Alignment,
) {
    val animatedValue = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(1500)
        animatedValue.animateTo(
            targetValue = value.toFloat(),
            animationSpec = tween(2000),
        )
    }

    Box(modifier = Modifier.weight(0.3f), contentAlignment = alignment) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentWidth(),
        ) {
            Text(
                text = animatedValue.value.toInt().toString(),
                fontSize = 22.sp,
                color = FeedSecondary,
                fontWeight = FontWeight.Bold,
            )
            Text(text = label, fontSize = 14.sp, color = FeedSecondary)
        }
    }
}
