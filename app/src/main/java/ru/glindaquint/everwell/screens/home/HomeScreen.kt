package ru.glindaquint.everwell.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.screens.home.components.simpleCalendar.SimpleCalendar
import ru.glindaquint.everwell.screens.home.components.topAppBar.HomeTopAppBar
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.pxToDp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    val sheetScaffoldState =
        rememberBottomSheetScaffoldState(rememberStandardBottomSheetState(SheetValue.PartiallyExpanded))
    val background =
        remember {
            derivedStateOf {
                when (sheetScaffoldState.bottomSheetState.targetValue.name == "Expanded") {
                    true -> Color.Black.copy(0.5f)
                    else -> Color.Transparent
                }
            }
        }
    val sheetWidth = remember { mutableIntStateOf(0) }
    val backgroundAnimated =
        animateColorAsState(
            targetValue = background.value,
            label = "BG darker anim",
            animationSpec = tween(400),
        )
    val topAppBarHeight = remember { mutableIntStateOf(0) }
    val infoWidgetSize = remember { mutableStateOf(IntSize(0, 0)) }

    BottomSheetScaffold(
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContainerColor = MainOnBackground,
        sheetDragHandle = {
            Spacer(
                modifier =
                    Modifier
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .width(100.dp)
                        .height(3.dp)
                        .background(MainPrimary),
            )
        },
        sheetContent = {
            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .navigationBarsPadding()
                        .onGloballyPositioned {
                            sheetWidth.intValue = it.size.width
                        },
                verticalArrangement = Arrangement.spacedBy(35.dp),
            ) {
                FlowRow(
                    maxItemsInEachRow = 4,
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy((sheetWidth.intValue.pxToDp() - 85.dp * 4) / 3.04f),
                ) {
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                    Box(contentAlignment = Alignment.BottomStart) {
                        Box(
                            modifier =
                                Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                        )
                        Text(
                            text = "Notes",
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            color = MainSecondary,
                            fontSize = 12.sp,
                        )
                    }
                }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        modifier =
                            Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF326779), Color(0xff0C2342)),
                                        start =
                                            Offset(
                                                x = infoWidgetSize.value.width / 2f,
                                                y = 0f,
                                            ),
                                    ),
                                ).onGloballyPositioned {
                                    infoWidgetSize.value = it.size
                                },
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier =
                                Modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = "Calories",
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = MainSecondary,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.apple),
                                contentDescription = "Calories",
                                modifier = Modifier.size(50.dp),
                                tint = MainSecondary,
                            )
                            Text(text = "5000 kCal", color = MainAccent, fontSize = 20.sp)
                        }
                    }
                    Column(
                        modifier =
                            Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF326779), Color(0xff0C2342)),
                                        start =
                                            Offset(
                                                x = infoWidgetSize.value.width / 2f,
                                                y = 0f,
                                            ),
                                    ),
                                ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier =
                                Modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = "Steps behind",
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = MainSecondary,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.sneakers),
                                contentDescription = "steps",
                                modifier = Modifier.size(50.dp),
                                tint = MainSecondary,
                            )
                            Text(text = "20 000", color = MainAccent, fontSize = 20.sp)
                        }
                    }
                    Column(
                        modifier =
                            Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF326779), Color(0xff0C2342)),
                                        start =
                                            Offset(
                                                x = infoWidgetSize.value.width / 2f,
                                                y = 0f,
                                            ),
                                    ),
                                ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier =
                                Modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = "Water drunk",
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = MainSecondary,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.water_glass),
                                contentDescription = "water",
                                modifier = Modifier.size(50.dp),
                                tint = MainSecondary,
                            )
                            Text(text = "1 liter", color = MainAccent, fontSize = 20.sp)
                        }
                    }
                }
            }
        },
        topBar = {
            Box {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(topAppBarHeight.intValue.pxToDp())
                            .background(backgroundAnimated.value)
                            .zIndex(1f),
                )
                HomeTopAppBar(
                    modifier =
                        Modifier.onGloballyPositioned {
                            topAppBarHeight.intValue = it.size.height
                        },
                    onMenuButtonClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                )
            }
        },
    ) { padding ->
        Box {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .background(backgroundAnimated.value)
                        .zIndex(1f),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MainBackground)
                        .padding(padding)
                        .padding(horizontal = 16.dp, vertical = 10.dp),
            ) {
                SimpleCalendar()

                // Tasks
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MainPrimary)
                                .padding(start = 12.dp)
                                .background(MainSecondary),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .padding(start = 10.dp)
                                    .padding(vertical = 15.dp),
                        ) {
                            Text(text = "Create a hextech", fontSize = 14.sp)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Text(
                                    text = "10:00",
                                    fontSize = 10.sp,
                                    lineHeight = 10.sp,
                                    color = MainPrimary,
                                )
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "is notification set",
                                    modifier = Modifier.size(13.dp),
                                    tint = MainPrimary,
                                )
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "is notification repeats",
                                    modifier = Modifier.size(13.dp),
                                    tint = MainPrimary,
                                )
                            }
                        }
                        Checkbox(
                            checked = true,
                            onCheckedChange = {},
                            colors =
                                CheckboxDefaults.colors(
                                    checkedColor = MainPrimary,
                                    uncheckedColor = MainPrimary,
                                ),
                        )
                    }
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MainPrimary)
                                .padding(start = 12.dp)
                                .background(MainSecondary),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .padding(start = 10.dp)
                                    .padding(vertical = 15.dp),
                        ) {
                            Text(text = "Save Viktor", fontSize = 14.sp)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Text(
                                    text = "10:00",
                                    fontSize = 10.sp,
                                    lineHeight = 10.sp,
                                    color = MainPrimary,
                                )
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "is notification set",
                                    modifier = Modifier.size(13.dp),
                                    tint = MainPrimary,
                                )
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "is notification repeats",
                                    modifier = Modifier.size(13.dp),
                                    tint = MainPrimary,
                                )
                            }
                        }
                        Checkbox(
                            checked = true,
                            onCheckedChange = {},
                            colors =
                                CheckboxDefaults.colors(
                                    checkedColor = MainPrimary,
                                    uncheckedColor = MainPrimary,
                                ),
                        )
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "All tasks", color = MainPrimary)
                    }
                }
            }
        }
    }
}
