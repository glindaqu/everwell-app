package ru.glindaquint.everwell.screens.feed

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedCartScreen(navHostController: NavHostController) {
    val selectedProducts =
        navHostController.previousBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<List<FeedProductDto>?>(
                "selected_products",
                null,
            )?.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("", "FeedCartScreen: ${selectedProducts?.value}")
    }

    EverwellScaffold(
        containerColor = FeedBackground,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Cart",
                colors =
                    MainTopBarColors(
                        backgroundColor = FeedPrimary,
                        foregroundColor = Color.White,
                        behindContainerColor = FeedBackground,
                    ),
                onIconClick = {
                    navHostController.navigateUp()
                },
            )
        },
        contentPadding = PaddingValues(10.dp),
        contentSpacing = Arrangement.spacedBy(10.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            selectedProducts?.value?.forEach { product ->
                FeedProductsListItem(product = product, onClick = {})
            }
        }
    }
}
