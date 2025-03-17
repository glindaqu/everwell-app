package ru.glindaquint.everwell.screens.feed.progresses

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.CircularProgress(
    progress: Int,
    maxProgress: Int,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }
    val caloriesLeft =
        when {
            maxProgress - progress < 0 -> 0
            else -> maxProgress - progress
        }
    val sweepAngle =
        when {
            progress * 260f / maxProgress > 260f -> 260f
            else -> progress * 260f / maxProgress
        }
    val sweepAngleAnimated = remember { Animatable(0f) }
    val caloriesLeftAnimated = remember { Animatable(maxProgress.toFloat()) }

    LaunchedEffect(Unit) {
        delay(1500)
        launch {
            sweepAngleAnimated.animateTo(
                targetValue = sweepAngle,
                animationSpec = tween(2000),
                initialVelocity = 260f,
            )
        }
        launch {
            caloriesLeftAnimated.animateTo(
                targetValue = caloriesLeft.toFloat(),
                animationSpec = tween(2000),
            )
        }
    }

    Box(
        modifier =
            Modifier
                .weight(0.4f)
                .onGloballyPositioned {
                    viewSize.value = it.size
                },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier =
                Modifier
                    .size(viewSize.value.width.pxToDp()),
        ) {
            val backgroundArc =
                Path().apply {
                    arcTo(
                        rect =
                            Rect(
                                left = 0f,
                                top = 0f,
                                right = size.width,
                                bottom = size.height,
                            ),
                        startAngleDegrees = 140f,
                        sweepAngleDegrees = 260f,
                        forceMoveTo = true,
                    )
                }
            val foregroundArc =
                Path().apply {
                    arcTo(
                        rect =
                            Rect(
                                left = 0f,
                                top = 0f,
                                right = size.width,
                                bottom = size.height,
                            ),
                        startAngleDegrees = 140f,
                        sweepAngleDegrees = sweepAngleAnimated.value,
                        forceMoveTo = true,
                    )
                }

            drawPath(
                path = backgroundArc,
                color = FeedOnBackground,
                style =
                    Stroke(
                        width = 20f,
                        cap = StrokeCap.Round,
                    ),
                blendMode = BlendMode.Lighten,
            )
            drawPath(
                path = foregroundArc,
                color = FeedPrimary,
                style =
                    Stroke(
                        width = 20f,
                        cap = StrokeCap.Round,
                    ),
                blendMode = BlendMode.SrcOver,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = caloriesLeftAnimated.value.toInt().toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = FeedSecondary,
            )
            Text(
                text = "Осталось",
                fontSize = 14.sp,
                color = FeedSecondary,
            )
        }
    }
}
