package ru.glindaquint.everwell.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.screens.home.components.bottomSheet.BottomSheet
import ru.glindaquint.everwell.screens.home.components.bottomSheet.BottomSheetDragHandle
import ru.glindaquint.everwell.screens.home.components.simpleCalendar.SimpleCalendar
import ru.glindaquint.everwell.screens.home.components.taskList.TaskList
import ru.glindaquint.everwell.screens.home.components.topAppBar.HomeTopAppBar
import ru.glindaquint.everwell.screens.home.components.tranparencyDarkBG.TransparencyDarkBackground
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.utils.pxToDp

@OptIn(ExperimentalMaterial3Api::class)
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
    val backgroundAnimated =
        animateColorAsState(
            targetValue = background.value,
            label = "BG darker anim",
            animationSpec = tween(400),
        )
    val topAppBarHeight = remember { mutableIntStateOf(0) }

    BottomSheetScaffold(
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContainerColor = MainOnBackground,
        sheetDragHandle = { BottomSheetDragHandle() },
        sheetContent = { BottomSheet() },
        topBar = {
            TransparencyDarkBackground(
                color = backgroundAnimated.value,
                modifier = Modifier.height(topAppBarHeight.intValue.pxToDp()),
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
        },
    ) { padding ->
        TransparencyDarkBackground(color = backgroundAnimated.value)
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MainBackground)
                    .padding(padding)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
        ) {
            SimpleCalendar()
            TaskList()
        }
    }
}
