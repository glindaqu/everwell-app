package ru.glindaquint.everwell.navigation.main

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.glindaquint.everwell.screens.home.HomeScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainNavHost(drawerState: DrawerState) {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = MainRoutes.home.routeName) {
        composable(route = MainRoutes.home.routeName) { HomeScreen(drawerState = drawerState) }
    }
}
