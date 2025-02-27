package ru.glindaquint.everwell.navigation.authorization

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.screens.authorization.restore.NewPasswordScreen
import ru.glindaquint.everwell.screens.authorization.restore.RestoreScreen
import ru.glindaquint.everwell.screens.authorization.signIn.SignInScreen
import ru.glindaquint.everwell.screens.authorization.signUp.ConfirmEmailScreen
import ru.glindaquint.everwell.screens.authorization.signUp.SignUpScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Suppress("ktlint:standard:function-naming")
@Composable
fun AuthorizationNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AuthorizationRoutes.RESTORE,
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
        composable(
            route = "${AuthorizationRoutes.CONFIRM_EMAIL}/{user}",
            content = { backStackEntry ->
                val request = SignUpRequest.fromJson(backStackEntry.arguments?.getString("user"))
                ConfirmEmailScreen(request = request)
            },
        )
    }
}
