package ru.glindaquint.everwell.screens.home.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.ActivityInfo
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.DailyAdvice
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.QuickAction
import ru.glindaquint.everwell.utils.pxToDp

@OptIn(ExperimentalLayoutApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomSheet() {
    val sheetWidth = remember { mutableStateOf(IntSize(0, 0)) }
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
        FlowRow(
            maxItemsInEachRow = 4,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy((sheetWidth.value.width.pxToDp() - 85.dp * 4) / 3.04f),
        ) {
            QuickAction(title = "Notes")
            QuickAction(title = "Notes")
            QuickAction(title = "Notes")
            QuickAction(title = "Notes")
            QuickAction(title = "Notes")
            QuickAction(title = "Notes")
        }

        DailyAdvice()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ActivityInfo(
                title = "Calories",
                painter = painterResource(id = R.drawable.apple),
                value = "2000kCal",
            )
            ActivityInfo(
                title = "Steps",
                painter = painterResource(id = R.drawable.sneakers),
                value = "20000",
            )
            ActivityInfo(
                title = "Water drunk",
                painter = painterResource(id = R.drawable.water_glass),
                value = "1 liter",
            )
        }
    }
}
