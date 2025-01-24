package ru.glindaquint.everwell.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("ktlint:standard:function-naming")
@Composable
fun UpdateStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(color)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun UpdateNavigationBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setNavigationBarColor(color)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun UpdateSystemBarsColor(
    statusBarColor: Color,
    navBarColor: Color,
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(statusBarColor)
        systemUiController.setNavigationBarColor(navBarColor)
    }
}
