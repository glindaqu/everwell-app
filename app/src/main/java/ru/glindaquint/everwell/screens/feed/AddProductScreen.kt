package ru.glindaquint.everwell.screens.feed

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductAddScreen(navHostController: NavHostController) {
    EverwellScaffold(containerColor = FeedBackground, topBar = {
        MainTopBar(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            title = "Add product",
            colors =
                MainTopBarColors(
                    backgroundColor = FeedPrimary,
                    foregroundColor = Color.White,
                    behindContainerColor = FeedBackground,
                ),
            onIconClick = { navHostController.navigateUp() },
        )
    }) {
    }
}
