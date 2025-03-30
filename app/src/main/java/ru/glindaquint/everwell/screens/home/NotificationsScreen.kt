package ru.glindaquint.everwell.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.viewModels.impl.NotificationsViewModel
import kotlin.random.Random

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotificationScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<NotificationsViewModel>()
    val categories = viewModel.categories.collectAsState()

    EverwellScaffold(
        containerColor = MainBackground,
        contentPadding = PaddingValues(10.dp),
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Notifications",
                colors =
                    MainTopBarColors(
                        backgroundColor = MainPrimary,
                        foregroundColor = MainSecondary,
                        behindContainerColor = MainBackground,
                    ),
                onIconClick = {
                    navHostController.navigateUp()
                },
            )
        },
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(categories.value) { category ->
                NotificationCategoryItem(title = category, count = Random.nextInt(0, 4))
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotificationCategoryItem(
    title: String,
    count: Int,
) {
    Row(
        modifier =
            Modifier
                .clip(CircleShape)
                .clickable { }
                .background(
                    color = MainOnBackground,
                    shape = CircleShape,
                ).padding(
                    horizontal = 10.dp,
                    vertical = 5.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Text(text = title, color = MainAccent)

        if (count > 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    Modifier
                        .size(22.dp)
                        .background(color = MainSecondary, shape = CircleShape),
            ) {
                Text(
                    text = count.toString(),
                    color = MainPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}
