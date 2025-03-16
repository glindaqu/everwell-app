package ru.glindaquint.everwell.screens.feed

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePickerColors
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.rememberSliderDatePickerState
import ru.glindaquint.everwell.ui.theme.FeedAccent
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedScreen(drawerState: DrawerState) {
    val sliderDatePickerState = rememberSliderDatePickerState()

    EverwellScaffold(
        drawerState = drawerState,
        topBarTitle = "Feed",
        topBarColors =
            MainTopBarColors(
                backgroundColor = FeedPrimary,
                foregroundColor = FeedOnBackground,
                behindContainerColor = FeedBackground,
            ),
        containerColor = FeedBackground,
        contentSpacing = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
    ) {
        NutritionDashboard()
        SliderDatePicker(
            state = sliderDatePickerState,
            colors =
                SliderDatePickerColors(
                    dayBackgroundColor = FeedPrimary,
                    dayForegroundColor = FeedOnBackground,
                    monthColor = FeedSecondary,
                    actionsColor = FeedSecondary,
                    daySelectedBackgroundColor = FeedSecondary.copy(0.5f),
                ),
            onDateSelected = {},
        )
        FeedManagementWidget()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedManagementWidget() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_breakfast),
            title = "Breakfast",
            content = "Egg, tea",
            placeholder = "Add breakfast",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_lunch),
            title = "Lunch",
            content = "",
            placeholder = "Add lunch",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_dinner),
            title = "Dinner",
            content = "Egg, tea",
            placeholder = "Add dinner",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_snack),
            title = "Snack",
            content = "",
            placeholder = "Add snack",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.fire),
            title = "Activity",
            content = "",
            placeholder = "Add activity",
        )
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
                }.background(
                    brush =
                        Brush.verticalGradient(
                            colors = listOf(Color(0xffE7F59E), Color(0xff153D27)),
                            startY = viewSize.value.height * 0.33f,
                            endY = viewSize.value.height * 1.3f,
                        ),
                    shape = RoundedCornerShape(12.dp),
                ).clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 10.dp, vertical = 25.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NutritionCard(value = 1431, label = "Съедено", alignment = Alignment.CenterStart)
                CircularProgress(progress = 145, maxProgress = 200)
                NutritionCard(value = 0, label = "Сожжено", alignment = Alignment.CenterEnd)
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                NutritionProgress(
                    label = "Углеводы",
                    progress = 142,
                    maxProgress = 192,
                    units = "гр",
                )
                NutritionProgress(label = "Белки", progress = 84, maxProgress = 77, units = "гр")
                NutritionProgress(label = "Жиры", progress = 50, maxProgress = 51, units = "гр")
            }
        }
    }
}

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
        LinearProgressBar(value = animatedValue.value / maxProgress)
        Text(
            text = "${animatedValue.value.toInt()}/$maxProgress$units",
            color = FeedOnBackground,
            fontSize = 12.sp,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun LinearProgressBar(value: Float) {
    val trustedValue =
        when {
            value > 1f -> 1f
            else -> value
        }

    Canvas(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        val backgroundScale =
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
            }

        val foregroundScale =
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * trustedValue, 0f)
            }

        drawPath(
            path = backgroundScale,
            color = FeedOnBackground,
            style =
                Stroke(
                    width = 10f,
                    cap = StrokeCap.Round,
                ),
        )
        drawPath(
            path = foregroundScale,
            color = FeedAccent,
            style =
                Stroke(
                    width = 10f,
                    cap = StrokeCap.Round,
                ),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun AddActivityTile(
    backgroundColor: Color,
    spacerColor: Color,
    icon: Painter,
    title: String,
    content: String,
    placeholder: String,
) {
    val buttonSize = remember { mutableStateOf(IntSize(0, 0)) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = icon, contentDescription = "Tile icon", tint = FeedSecondary)
            Text(text = title, color = FeedSecondary, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(spacerColor))
        Row(
            modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (content.isEmpty()) {
                Text(
                    text = placeholder,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .padding(start = buttonSize.value.width.pxToDp()),
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(text = content)
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier =
                    Modifier
                        .wrapContentSize()
                        .onGloballyPositioned {
                            buttonSize.value = it.size
                        },
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add new activity",
                    tint = FeedPrimary,
                    modifier = Modifier.fillMaxSize(0.75f),
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun Test_Tile() {
    Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_breakfast),
            title = "Breakfast",
            content = "Egg, tea",
            placeholder = "Add breakfast",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_breakfast),
            title = "Breakfast",
            content = "",
            placeholder = "Add breakfast",
        )
    }
}
