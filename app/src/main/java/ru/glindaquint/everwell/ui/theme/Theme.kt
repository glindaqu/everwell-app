package ru.glindaquint.everwell.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ru.glindaquint.everwell.utils.SystemBarsUtils

@Suppress("ktlint:standard:function-naming")
@Composable
fun EverwellTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SystemBarsUtils.setStatusBarColor(view.context as Activity, MainPrimary)
                SystemBarsUtils.setNavigationBarColor(
                    view.context as Activity,
                    MainBackground.copy(0.0f),
                )
                WindowCompat
                    .getInsetsController(
                        activity.window,
                        view,
                    ).isAppearanceLightStatusBars = true
                WindowCompat
                    .getInsetsController(
                        activity.window,
                        view,
                    ).isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
