package ru.glindaquint.everwell.navigation.main

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.screens.feed.FeedScreen
import ru.glindaquint.everwell.screens.home.HomeScreen
import ru.glindaquint.everwell.screens.pressure.BloodPressureScreen
import ru.glindaquint.everwell.screens.profile.ProfileInfoScreen
import ru.glindaquint.everwell.screens.profile.ProfileScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainNavHost(
    drawerState: DrawerState,
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = MainRoutes.home.routeName) {
        composable(route = MainRoutes.home.routeName) {
            HomeScreen(
                drawerState = drawerState,
                navHostController = navHostController,
            )
        }
        composable(route = MainRoutes.pressure.routeName) { BloodPressureScreen(drawerState = drawerState) }
        composable(route = MainRoutes.feed.routeName) { FeedScreen(drawerState = drawerState) }
        composable(route = MainRoutes.profile.routeName) { ProfileScreen(navHostController = navHostController) }
        composable(route = MainRoutes.profileInfo.routeName) {
            ProfileInfoScreen(navHostController = navHostController)
        }
    }
}
