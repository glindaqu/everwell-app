package ru.glindaquint.everwell.screens.home.components.bottomSheet

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.ActivityInfo
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.QuickAction
import ru.glindaquint.everwell.utils.pxToDp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomSheet(
    state: SheetState,
    onWidgetPlaced: (Dp) -> Unit,
) {
    val sheetWidth = remember { mutableStateOf(IntSize(0, 0)) }
    val quickActionModifier = Modifier.size((sheetWidth.value.width / 4).pxToDp() - 10.dp)
    val widgetModifier = Modifier.size((sheetWidth.value.width / 3).pxToDp() - 10.dp)

    val targetMainAlpha =
        remember {
            derivedStateOf {
                when {
                    state.targetValue.name == "Expanded" -> 1f
                    else -> 0f
                }
            }
        }

    val mainContentAlpha =
        animateFloatAsState(
            targetValue = targetMainAlpha.value,
            label = "Alpha anim for main content",
            animationSpec = tween(400),
        )

    LaunchedEffect(sheetWidth) {
        onWidgetPlaced((sheetWidth.value.width / 3).pxToDp())
    }

    Column(
        modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
                .onGloballyPositioned {
                    sheetWidth.value = it.size
                },
        verticalArrangement = Arrangement.spacedBy(35.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            QuickAction(title = "Notes", modifier = quickActionModifier)
            QuickAction(title = "Notes", modifier = quickActionModifier)
            QuickAction(title = "Notes", modifier = quickActionModifier)
            QuickAction(title = "Notes", modifier = quickActionModifier)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(35.dp),
            modifier = Modifier.alpha(mainContentAlpha.value),
        ) {
//            DailyAdvice(advice = viewModel.bottomSheetUiState.value.dailyAdvice)
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ActivityInfo(
                    modifier = widgetModifier,
                    title = "Calories",
                    painter = painterResource(id = R.drawable.apple),
                    value = "1000kCal",
                )
                ActivityInfo(
                    modifier = widgetModifier,
                    title = "Steps",
                    painter = painterResource(id = R.drawable.sneakers),
                    value = "0",
                )
                ActivityInfo(
                    modifier = widgetModifier,
                    title = "Water drunk",
                    painter = painterResource(id = R.drawable.water_glass),
                    value = "1 liters",
                )
            }
        }
    }
}
