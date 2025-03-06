package ru.glindaquint.everwell.sharedComponents.menu

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.navigation.main.MainRoute
import ru.glindaquint.everwell.navigation.main.MainRouteDto
import ru.glindaquint.everwell.navigation.main.MainRoutes

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationDrawerContainer(
    navigationItems: List<MainRouteDto> =
        MainRoutes::class.java.declaredFields
            .toList()
            .filter { field ->
                return@filter field.isAnnotationPresent(MainRoute::class.java)
            }.map { field ->
                field.isAccessible = true
                field.get(MainRoutes.Companion) as MainRouteDto
            }.sortedBy { field ->
                field.routeId
            },
    navHostController: NavHostController,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationSheet(
                navigationItems = navigationItems,
                onItemClick = {
                    scope.launch {
                        drawerState.close()
                        navHostController.navigate(it.routeName)
                    }
                },
            )
        },
        content = { content() },
        scrimColor = Color.Black.copy(0.5f),
    )
}
