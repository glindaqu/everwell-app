package ru.glindaquint.everwell.navigation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.screens.feed.FeedCartScreen
import ru.glindaquint.everwell.screens.feed.FeedScreen
import ru.glindaquint.everwell.screens.feed.ProductAddScreen
import ru.glindaquint.everwell.screens.feed.ProductInfoScreen
import ru.glindaquint.everwell.screens.feed.ProductSearchScreen
import ru.glindaquint.everwell.screens.home.HomeScreen
import ru.glindaquint.everwell.screens.home.NotificationScreen
import ru.glindaquint.everwell.screens.pressure.BloodPressureScreen
import ru.glindaquint.everwell.screens.profile.ProfileInfoScreen
import ru.glindaquint.everwell.screens.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
fun MainNavHost(
    drawerState: DrawerState,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MainRoutes.home.routeName,
    ) {
        composable(route = MainRoutes.home.routeName) {
            HomeScreen(
                drawerState = drawerState,
                navHostController = navHostController,
            )
        }

        composable(route = MainRoutes.pressure.routeName) {
            BloodPressureScreen(drawerState = drawerState)
        }

        composable(route = MainRoutes.feed.routeName) {
            FeedScreen(
                drawerState = drawerState,
                navHostController = navHostController,
            )
        }

        composable(route = MainRoutes.feedSearchProduct.routeName) {
            ProductSearchScreen(navHostController = navHostController)
        }

        composable(route = "${MainRoutes.feedProductInfo.routeName}/{id}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            ProductInfoScreen(navHostController = navHostController, productId = productId ?: -1)
        }

        composable(route = MainRoutes.profile.routeName) {
            ProfileScreen(
                navHostController = navHostController,
                drawerState = drawerState,
            )
        }

        composable(route = MainRoutes.profileInfo.routeName) {
            ProfileInfoScreen(navHostController = navHostController)
        }

        composable(route = MainRoutes.notifications.routeName) {
            NotificationScreen(navHostController = navHostController)
        }

        composable(route = MainRoutes.feedAddProduct.routeName) {
            ProductAddScreen(navHostController = navHostController)
        }

        composable(route = MainRoutes.feedCart.routeName) {
            FeedCartScreen(navHostController = navHostController)
        }
    }
}
