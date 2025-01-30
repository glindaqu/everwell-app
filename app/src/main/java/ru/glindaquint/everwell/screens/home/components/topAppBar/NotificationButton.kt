package ru.glindaquint.everwell.screens.home.components.topAppBar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun NotificationButton(onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.padding(end = 42.pxToDp()),
    ) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Go to notifications",
            modifier =
                Modifier
                    .rotate(30f)
                    .size(24.dp),
        )
    }
}
