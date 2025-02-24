package ru.glindaquint.everwell.navigation.authorization

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.screens.authorization.restore.NewPasswordScreen
import ru.glindaquint.everwell.screens.authorization.restore.RestoreScreen
import ru.glindaquint.everwell.screens.authorization.signIn.SignInScreen
import ru.glindaquint.everwell.screens.authorization.signUp.SignUpScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun AuthorizationNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AuthorizationRoutes.SIGN_UP,
    ) {
        composable(
            route = AuthorizationRoutes.SIGN_IN,
            content = { SignInScreen(navHostController = navHostController) },
        )
        composable(
            route = AuthorizationRoutes.SIGN_UP,
            content = { SignUpScreen(navHostController = navHostController) },
        )
        composable(
            route = AuthorizationRoutes.RESTORE,
            content = { RestoreScreen(navHostController = navHostController) },
        )
        composable(
            route = AuthorizationRoutes.NEW_PASSWORD,
            content = { NewPasswordScreen() },
        )
    }
}
