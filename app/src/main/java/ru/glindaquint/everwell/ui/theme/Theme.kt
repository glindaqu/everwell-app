package ru.glindaquint.everwell.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("ktlint:standard:function-naming")
@Composable
fun EverwellTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val view = LocalView.current

    SideEffect {
        systemUiController.setStatusBarColor(MainPrimary)
        WindowCompat
            .getInsetsController(
                (view.context as Activity).window,
                view,
            ).isAppearanceLightStatusBars = true
        WindowCompat
            .getInsetsController(
                (view.context as Activity).window,
                view,
            ).isAppearanceLightNavigationBars = true
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
