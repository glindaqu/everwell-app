package ru.glindaquint.everwell.utils

import android.app.Activity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class SystemBarsUtils {
    companion object {
        fun setStatusBarColor(
            activity: Activity,
            color: Color,
        ) {
            val window = activity.window
            window.statusBarColor = color.toArgb()
        }

        fun setNavigationBarColor(
            activity: Activity,
            color: Color,
        ) {
            val window = activity.window
            window.navigationBarColor = color.toArgb()
        }
    }
}
