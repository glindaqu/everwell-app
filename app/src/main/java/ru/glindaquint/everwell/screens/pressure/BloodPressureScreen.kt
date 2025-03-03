package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureBackground
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary
import ru.glindaquint.everwell.ui.theme.BloodPressureSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScreen() {
    val calendarSliderSize = remember { mutableStateOf(IntSize(0, 0)) }

    Scaffold(
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = BloodPressurePrimary,
                            shape = RoundedCornerShape(bottomEnd = 18.dp, bottomStart = 18.dp),
                        ).statusBarsPadding()
                        .padding(vertical = 25.pxToDp()),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Show/Hide menu",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White,
                    )
                }
                Text(
                    text = "Давление",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        },
        containerColor = BloodPressureBackground,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues),
        ) {
            BloodPressure()
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(top = 25.dp)
                        .background(
                            color = BloodPressureSecondary,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .drawWithCache {
                                val brush =
                                    Brush.linearGradient(
                                        colors = listOf(BloodPressurePrimary, Color(0xffFF9E81)),
                                        start =
                                            Offset(
                                                x = calendarSliderSize.value.width / 2f,
                                                y = calendarSliderSize.value.height / 3f,
                                            ),
                                        end =
                                            Offset(
                                                x = calendarSliderSize.value.width / 2f,
                                                y = calendarSliderSize.value.height.toFloat(),
                                            ),
                                    )
                                onDrawBehind {
                                    drawRoundRect(
                                        brush,
                                        cornerRadius = CornerRadius(250.dp.toPx(), 45.dp.toPx())
                                    )
                                }
                            }.onGloballyPositioned {
                                calendarSliderSize.value = it.size
                            },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "prev month",
                                tint = BloodPressureAccent,
                            )
                        }
                        Text(text = "JANUARY 2025", color = Color.White)
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = "next month",
                                tint = BloodPressureAccent,
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(start = 16.dp, bottom = 35.dp),
                    ) {
                        items((1..30).toList()) {
                            Box(
                                modifier =
                                    Modifier.size(42.dp).background(
                                        color = BloodPressureAccent,
                                        shape = CircleShape,
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(text = it.toString(), fontSize = 16.sp, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressure() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        modifier =
            Modifier
                .padding(top = 11.dp)
                .padding(horizontal = 16.dp),
    ) {
        BloodPressureMeasureField(title = "САД", value = "119", subtitle = "мм. рт. ст.")
        BloodPressureMeasureField(title = "ДАД", value = "79", subtitle = "мм. рт. ст.")
        BloodPressureMeasureField(title = "Пульс", value = "70", subtitle = "ударов/мин")
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.BloodPressureMeasureField(
    title: String,
    value: String,
    subtitle: String,
) {
    val componentWidth = remember { mutableIntStateOf(0) }

    Column(
        modifier =
            Modifier
                .weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        componentWidth.intValue = it.size.width
                    }.height(componentWidth.intValue.pxToDp())
                    .background(
                        color = BloodPressureOnBackground,
                        shape = RoundedCornerShape(12.dp),
                    ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = value, fontSize = 40.sp, color = Color.White)
            Text(text = subtitle, fontSize = 12.sp, color = BloodPressureAccent)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun Test_Pressure() {
    BloodPressureScreen()
}
