package ru.glindaquint.everwell.screens.feed

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.FeedAccent
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedScreen(drawerState: DrawerState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            MainTopBar(
                drawerState = drawerState,
                icon = Icons.Filled.Menu,
                title = "Питание",
                colors =
                MainTopBarColors(
                    backgroundColor = FeedPrimary,
                    foregroundColor = FeedOnBackground,
                    behindContainerColor = FeedBackground,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(FeedBackground)
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp)
                .navigationBarsPadding(),
        ) {
            NutritionDashboard()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NutritionDashboard() {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }

    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                viewSize.value = it.size
            }
            .background(
                brush =
                Brush.verticalGradient(
                    colors = listOf(Color(0xffE7F59E), Color(0xff153D27)),
                    startY = viewSize.value.height * 0.33f,
                    endY = viewSize.value.height * 1.3f
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 25.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NutritionCard(value = "1431", label = "Съедено", alignment = Alignment.CenterStart)
                CircularProgress(progress = 145, maxProgress = 200)
                NutritionCard(value = "0", label = "Сожжено", alignment = Alignment.CenterEnd)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                NutritionProgress(label = "Углеводы", progressText = "147/192г")
                NutritionProgress(label = "Белки", progressText = "84/77г")
                NutritionProgress(label = "Жиры", progressText = "50/51г")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.NutritionCard(
    value: String,
    label: String,
    alignment: Alignment
) {
    Box(modifier = Modifier.weight(0.3f), contentAlignment = alignment) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = value,
                fontSize = 22.sp,
                color = FeedSecondary,
                fontWeight = FontWeight.Bold
            )
            Text(text = label, fontSize = 14.sp, color = FeedSecondary)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.CircularProgress(
    progress: Int,
    maxProgress: Int,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }
    val caloriesLeft = when {
        maxProgress - progress < 0 -> 0
        else -> maxProgress - progress
    }
    val sweepAngle = when {
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
                initialVelocity = 260f
            )
        }
        launch {
            caloriesLeftAnimated.animateTo(
                targetValue = caloriesLeft.toFloat(),
                animationSpec = tween(2000)
            )
        }
    }

    Box(
        modifier = Modifier
            .weight(0.4f)
            .onGloballyPositioned {
                viewSize.value = it.size
            }, contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier =
            Modifier
                .size(viewSize.value.width.pxToDp()),
        ) {
            val backgroundArc = Path().apply {
                arcTo(
                    rect = Rect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height
                    ),
                    startAngleDegrees = 140f,
                    sweepAngleDegrees = 260f,
                    forceMoveTo = true
                )
            }
            val foregroundArc = Path().apply {
                arcTo(
                    rect = Rect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height
                    ),
                    startAngleDegrees = 140f,
                    sweepAngleDegrees = sweepAngleAnimated.value,
                    forceMoveTo = true
                )
            }

            drawPath(
                path = backgroundArc,
                color = FeedOnBackground,
                style = Stroke(
                    width = 20f,
                    cap = StrokeCap.Round
                ),
                blendMode = BlendMode.Lighten
            )
            drawPath(
                path = foregroundArc,
                color = FeedPrimary,
                style = Stroke(
                    width = 20f,
                    cap = StrokeCap.Round
                ),
                blendMode = BlendMode.SrcOver
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = caloriesLeftAnimated.value.toInt().toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = FeedSecondary
            )
            Text(
                text = "Осталось",
                fontSize = 14.sp,
                color = FeedSecondary
            )
        }
    }

}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.NutritionProgress(
    label: String,
    progressText: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = label, color = FeedOnBackground)
        LinearProgressBar(value = 0.6f)
        Text(text = progressText, color = FeedOnBackground, fontSize = 12.sp)
    }
}

@Composable
fun LinearProgressBar(value: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val backgroundScale = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
        }

        val foregroundScale = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width * value, 0f)
        }

        drawPath(
            path = backgroundScale,
            color = FeedOnBackground,
            style = Stroke(
                width = 10f,
                cap = StrokeCap.Round
            )
        )
        drawPath(
            path = foregroundScale,
            color = FeedAccent,
            style = Stroke(
                width = 10f,
                cap = StrokeCap.Round
            )
        )
    }
}

@Composable
@Preview
fun Test_CircularProgress() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NutritionCard(value = "1431", label = "Съедено", alignment = Alignment.CenterStart)
            CircularProgress(progress = 100, maxProgress = 200)
            NutritionCard(value = "0", label = "Сожжено", alignment = Alignment.CenterEnd)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            NutritionProgress(label = "Углеводы", progressText = "147/192г")
            NutritionProgress(label = "Белки", progressText = "84/77г")
            NutritionProgress(label = "Жиры", progressText = "50/51г")
        }
    }
}