package ru.glindaquint.everwell.sharedComponents.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.dto.colors.navigation.NavigationSheetItemColors
import ru.glindaquint.everwell.navigation.main.MainRouteDto
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.ui.theme.MainOnBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationSheet(
    navigationItems: List<MainRouteDto>,
    onItemClick: (MainRouteDto) -> Unit,
) {
    val selectedItem = remember { mutableStateOf(navigationItems[0]) }
    val metrics = LocalContext.current.resources.displayMetrics
    val itemColors =
        NavigationSheetItemColors(
            backgroundColor = selectedItem.value.navigationDrawerColors.itemBackgroundColor,
            foregroundColor =
                selectedItem.value.navigationDrawerColors.itemForegroundColor.copy(
                    alpha = 0.8f,
                ),
            selectedBackgroundColor = selectedItem.value.navigationDrawerColors.selectedItemColor,
            selectedForegroundColor = selectedItem.value.navigationDrawerColors.itemForegroundColor,
        )

    ModalDrawerSheet(
        drawerContainerColor = MainOnBackground,
        windowInsets = WindowInsets(0),
        modifier = Modifier.widthIn(max = (metrics.widthPixels / LocalDensity.current.density * 0.75f).dp),
    ) {
        Column(modifier = Modifier.fillMaxSize().navigationBarsPadding(), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                NavigationSheetHeader(navigationDrawerColors = selectedItem.value.navigationDrawerColors)
                NavigationSheetBody(
                    navigationItems = navigationItems,
                    navigationDrawerColors = selectedItem.value.navigationDrawerColors,
                    itemColorPalette = itemColors,
                    currentItem = selectedItem.value,
                    onItemClick = {
                        selectedItem.value = it
                        onItemClick(it)
                    },
                )
            }
            NavigationSheetItem(
                label = MainRoutes.about.routeTitleResource,
                icon = MainRoutes.about.routeIconResource,
                colors =
                    NavigationSheetItemColors(
                        backgroundColor = selectedItem.value.navigationDrawerColors.bodyColor,
                        selectedBackgroundColor = selectedItem.value.navigationDrawerColors.bodyColor,
                        foregroundColor = selectedItem.value.navigationDrawerColors.accentColor,
                        selectedForegroundColor = selectedItem.value.navigationDrawerColors.accentColor,
                    ),
                isSelected = selectedItem.value == MainRoutes.about,
                onClick = { },
            )
        }
    }
}
