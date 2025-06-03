package ru.glindaquint.everwell.screens.home.components.bottomSheet

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.ActivityInfo
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.DailyAdvice
import ru.glindaquint.everwell.screens.home.components.bottomSheet.widgets.QuickAction
import ru.glindaquint.everwell.services.stepCounter.StepCounterRepository
import ru.glindaquint.everwell.services.stepCounter.StepCounterService
import ru.glindaquint.everwell.utils.pxToDp
import ru.glindaquint.everwell.viewModels.impl.BottomSheetViewModel
import ru.glindaquint.everwell.viewModels.impl.FeedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomSheet(
    state: SheetState,
    navHostController: NavHostController,
    onWidgetPlaced: (Dp) -> Unit,
) {
    val context = LocalContext.current
    val currentSteps = StepCounterRepository.currentSteps.observeAsState()
    val totalSteps = StepCounterRepository.totalSteps.observeAsState()

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted ->
            if (isGranted) {
                StepCounterService.start(context)
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACTIVITY_RECOGNITION,
            ) == PackageManager.PERMISSION_GRANTED -> {
                StepCounterService.start(context)
            }

            else -> {
                permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        }
    }

    val feedViewModel = hiltViewModel<FeedViewModel>()
    val bottomSheetViewModel = hiltViewModel<BottomSheetViewModel>()

    val totalCalories =
        feedViewModel.uiState
            .collectAsState()
            .value.totalCalories
    val advice = bottomSheetViewModel.dailyAdvice.collectAsState()

    val sheetWidth = remember { mutableStateOf(IntSize(0, 0)) }
    val quickActionModifier = Modifier.size((sheetWidth.value.width / 4).pxToDp() - 10.dp)
    val widgetModifier = Modifier.size((sheetWidth.value.width / 3).pxToDp() - 10.dp)
    val widgetSpacing = (sheetWidth.value.width.pxToDp() - 8.dp - (85.dp * 4)) / 3

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
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(widgetSpacing),
            verticalArrangement = Arrangement.spacedBy(widgetSpacing),
        ) {
            QuickAction(
                title = "Blood Pressure",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.pressure_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.pressure.routeName)
                },
            )
            QuickAction(
                title = "Feed",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.feed_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.feed.routeName)
                },
            )
            QuickAction(
                title = "Water Balance",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.water_balance_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.waterBalance.routeName)
                },
            )
            QuickAction(
                title = "Notes",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.notes_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.notes.routeName)
                },
            )
            QuickAction(
                title = "Tasks",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.tasks_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.tasks.routeName)
                },
            )
            QuickAction(
                title = "News",
                modifier = quickActionModifier,
                painter = painterResource(R.drawable.news_widget),
                onClick = {
                    navHostController.navigate(MainRoutes.news.routeName)
                },
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(35.dp),
            modifier = Modifier.alpha(mainContentAlpha.value),
        ) {
            DailyAdvice(advice = advice.value)
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ActivityInfo(
                    modifier = widgetModifier,
                    title = "Calories",
                    painter = painterResource(id = R.drawable.apple),
                    value = "${totalCalories}kCal",
                )
                ActivityInfo(
                    modifier = widgetModifier,
                    title = "Steps",
                    painter = painterResource(id = R.drawable.sneakers),
                    value = currentSteps.value.toString(),
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
