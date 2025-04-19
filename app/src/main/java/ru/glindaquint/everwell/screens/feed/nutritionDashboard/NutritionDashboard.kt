package ru.glindaquint.everwell.screens.feed.nutritionDashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.screens.feed.progresses.CircularProgress

@Suppress("ktlint:standard:function-naming")
@Composable
fun NutritionDashboard(
    targetCalories: Int = 2000,
    currentCalories: Int,
    burntCalories: Int = 0,
    currentFat: Double,
    targetFat: Double = 50.0,
    currentProtein: Double,
    targetProtein: Double = 200.0,
    currentCarbohydrates: Double,
    targetCarbohydrates: Double = 300.0,
) {
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NutritionCard(
                    value = currentCalories,
                    label = "Съедено",
                    alignment = Alignment.CenterStart,
                )
                CircularProgress(progress = currentCalories, maxProgress = targetCalories)
                NutritionCard(
                    value = burntCalories,
                    label = "Сожжено",
                    alignment = Alignment.CenterEnd,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                NutritionProgress(
                    label = "Углеводы",
                    progress = currentCarbohydrates.toInt(),
                    maxProgress = targetCarbohydrates.toInt(),
                    units = "гр",
                )
                NutritionProgress(
                    label = "Белки",
                    progress = currentProtein.toInt(),
                    maxProgress = targetProtein.toInt(),
                    units = "гр",
                )
                NutritionProgress(
                    label = "Жиры",
                    progress = currentFat.toInt(),
                    maxProgress = targetFat.toInt(),
                    units = "гр",
                )
            }
        }
    }
}
