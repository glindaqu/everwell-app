package ru.glindaquint.everwell.sharedComponents.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.dto.colors.navigation.NavigationSheetItemColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationSheetItem(
    label: Int,
    icon: Int,
    colors: NavigationSheetItemColors,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val background = if (isSelected) colors.selectedBackgroundColor else colors.backgroundColor
    val foreground = if (isSelected) colors.selectedForegroundColor else colors.foregroundColor

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(background)
                .clickable { onClick() }
                .padding(vertical = 7.dp)
                .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Menu item",
            tint = foreground,
            modifier = Modifier.size(30.dp),
        )
        Text(text = stringResource(id = label), color = foreground)
    }
}
