package ru.glindaquint.everwell.screens.home.components.topAppBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeTopAppBar(
    username: String,
    modifier: Modifier = Modifier,
    onMenuButtonClick: () -> Unit,
    onNotificationButtonClick: () -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(MainBackground)
                .padding(top = 9.dp)
                .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onMenuButtonClick() },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Show/Hide menu",
                        modifier = Modifier.size(24.dp),
                    )
                },
            )
            ProfileInfo(username = username)
        }
        NotificationButton(onClick = { onNotificationButtonClick() })
    }
}
