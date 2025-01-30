package ru.glindaquint.everwell.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.screens.home.components.topAppBar.HomeTopAppBar
import ru.glindaquint.everwell.ui.theme.MainBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onMenuButtonClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
        contentWindowInsets =
            WindowInsets(
                top = 10.dp,
                left = 16.dp,
                right = 16.dp,
            ),
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MainBackground)
                    .padding(padding),
        ) {
        }
    }
}
