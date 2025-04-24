package ru.glindaquint.everwell.screens.feed.feedManagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.uiStates.Feeds
import ru.glindaquint.everwell.utils.FeedType

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedManagementWidget(
    navHostController: NavHostController,
    feeds: Feeds,
    readonly: Boolean = false,
) {
    val onClick: (FeedType) -> Unit = {
        navHostController.currentBackStackEntry?.savedStateHandle?.set("feed_type", it)
        navHostController.navigate(MainRoutes.feedSearchProduct.routeName)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_breakfast),
            title = "Breakfast",
            content = feeds.breakfast.joinToString(", "),
            placeholder = "Add breakfast",
            onClick = { onClick(FeedType.BREAKFAST) },
            readonly = readonly,
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_lunch),
            title = "Lunch",
            content = feeds.lunch.joinToString(", "),
            placeholder = "Add lunch",
            onClick = { onClick(FeedType.LUNCH) },
            readonly = readonly,
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_dinner),
            title = "Dinner",
            content = feeds.dinner.joinToString(", "),
            placeholder = "Add dinner",
            onClick = { onClick(FeedType.DINNER) },
            readonly = readonly,
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_snack),
            title = "Snack",
            content = feeds.snack.joinToString(", "),
            placeholder = "Add snack",
            onClick = { FeedType.SNACK },
            readonly = readonly,
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.fire),
            title = "Activity",
            content = "",
            placeholder = "Add activity",
            onClick = { },
            readonly = readonly,
        )
    }
}
