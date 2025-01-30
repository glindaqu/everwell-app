package ru.glindaquint.everwell.sharedComponents.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.glindaquint.everwell.navigation.main.MainRouteDto
import ru.glindaquint.everwell.sharedComponents.LineSpacer
import ru.glindaquint.everwell.types.NavigationDrawerColors
import ru.glindaquint.everwell.types.NavigationSheetItemColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationSheetBody(
    navigationItems: List<MainRouteDto>,
    navigationDrawerColors: NavigationDrawerColors,
    itemColorPalette: NavigationSheetItemColors,
    currentItem: MainRouteDto,
    onItemClick: (MainRouteDto) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
    ) {
        LineSpacer(color = Color.Transparent, firstPadding = 7)
        navigationItems.forEachIndexed { index, item ->
            if (index == navigationItems.size - 1) {
                LineSpacer(
                    color = navigationDrawerColors.accentColor,
                    firstPadding = 5,
                    secondPadding = 10,
                )
            }
            NavigationSheetItem(
                label = item.routeTitleResource,
                icon = item.routeIconResource,
                colors = itemColorPalette,
                isSelected = currentItem == item,
                onClick = { onItemClick(item) },
            )
            if (index == 0) {
                LineSpacer(color = navigationDrawerColors.accentColor)
            }
        }
    }
}
