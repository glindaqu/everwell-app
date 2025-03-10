package ru.glindaquint.everwell.navigation.main

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.screens.home.HomeScreen
import ru.glindaquint.everwell.screens.pressure.BloodPressureScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainNavHost(
    drawerState: DrawerState,
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = MainRoutes.home.routeName) {
        composable(route = MainRoutes.home.routeName) { HomeScreen(drawerState = drawerState) }
        composable(route = MainRoutes.pressure.routeName) { BloodPressureScreen(drawerState = drawerState) }
    }
}
