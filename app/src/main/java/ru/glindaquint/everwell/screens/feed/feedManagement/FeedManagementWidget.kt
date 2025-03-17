package ru.glindaquint.everwell.screens.feed.feedManagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.ui.theme.FeedOnBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedManagementWidget() {
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
            content = "Egg, tea",
            placeholder = "Add breakfast",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_lunch),
            title = "Lunch",
            content = "",
            placeholder = "Add lunch",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_dinner),
            title = "Dinner",
            content = "Egg, tea",
            placeholder = "Add dinner",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.feed_snack),
            title = "Snack",
            content = "",
            placeholder = "Add snack",
        )
        AddActivityTile(
            backgroundColor = FeedOnBackground,
            spacerColor = FeedPrimary,
            icon = painterResource(id = R.drawable.fire),
            title = "Activity",
            content = "",
            placeholder = "Add activity",
        )
    }
}
