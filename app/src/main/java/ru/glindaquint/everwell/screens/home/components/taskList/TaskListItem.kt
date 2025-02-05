package ru.glindaquint.everwell.screens.home.components.taskList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun TaskListItem(
    title: String,
    deadlineTime: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MainPrimary)
                .padding(start = 12.dp)
                .background(MainSecondary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier =
                Modifier
                    .padding(start = 10.dp)
                    .padding(vertical = 15.dp),
        ) {
            Text(text = title, fontSize = 14.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = deadlineTime,
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    color = MainPrimary,
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "is notification set",
                    modifier = Modifier.size(13.dp),
                    tint = MainPrimary,
                )
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "is notification repeats",
                    modifier = Modifier.size(13.dp),
                    tint = MainPrimary,
                )
            }
        }
        Checkbox(
            checked = true,
            onCheckedChange = {},
            colors =
                CheckboxDefaults.colors(
                    checkedColor = MainPrimary,
                    uncheckedColor = MainPrimary,
                ),
        )
    }
}
