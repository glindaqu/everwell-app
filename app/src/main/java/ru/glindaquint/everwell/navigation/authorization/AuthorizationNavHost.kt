package ru.glindaquint.everwell.navigation.authorization

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.screens.authorization.restore.RestoreScreen
import ru.glindaquint.everwell.screens.authorization.signIn.SignInScreen
import ru.glindaquint.everwell.screens.authorization.signUp.SignUpScreen

@RequiresApi(Build.VERSION_CODES.R)
@Suppress("ktlint:standard:function-naming")
@Composable
fun AuthorizationNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = AuthorizationRoutes.SIGN_IN) {
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
    }
}
