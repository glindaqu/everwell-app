package ru.glindaquint.everwell.screens.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.screens.feed.feedManagement.FeedManagementWidget
import ru.glindaquint.everwell.screens.feed.nutritionDashboard.NutritionDashboard
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePickerColors
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.rememberSliderDatePickerState
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedScreen(drawerState: DrawerState) {
    val sliderDatePickerState = rememberSliderDatePickerState()
    val coroutineScope = rememberCoroutineScope()

    EverwellScaffold(
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
        topBar = {
            MainTopBar(
                icon = Icons.Filled.Menu,
                title = "Feed",
                colors =
                    MainTopBarColors(
                        backgroundColor = FeedPrimary,
                        foregroundColor = FeedOnBackground,
                        behindContainerColor = FeedBackground,
                    ),
                onIconClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
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
