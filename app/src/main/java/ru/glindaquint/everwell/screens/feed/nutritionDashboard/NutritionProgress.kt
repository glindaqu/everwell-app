package ru.glindaquint.everwell.screens.feed.nutritionDashboard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.screens.feed.progresses.LinearProgress
import ru.glindaquint.everwell.ui.theme.FeedOnBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.NutritionProgress(
    label: String,
    maxProgress: Int,
    progress: Int,
    units: String,
) {
    val animatedValue = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(1500)
        animatedValue.animateTo(
            targetValue = progress.toFloat(),
            animationSpec = tween(2000),
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(text = label, color = FeedOnBackground)
        LinearProgress(value = animatedValue.value / maxProgress)
        Text(
            text = "${animatedValue.value.toInt()}/$maxProgress$units",
            color = FeedOnBackground,
            fontSize = 12.sp,
        )
    }
}
