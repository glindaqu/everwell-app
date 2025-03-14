package ru.glindaquint.everwell.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.ui.theme.BloodPressureBackground
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainTopBar(
    drawerState: DrawerState,
    icon: ImageVector,
    title: String,
    colors: MainTopBarColors,
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(BloodPressureBackground)
                .background(
                    color = colors.backgroundColor,
                    shape = RoundedCornerShape(bottomEnd = 18.dp, bottomStart = 18.dp),
                ).statusBarsPadding()
                .padding(vertical = 25.pxToDp()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            scope.launch {
                drawerState.open()
            }
        }) {
            Icon(
                imageVector = icon,
                contentDescription = "Show/Hide menu",
                modifier = Modifier.size(24.dp),
                tint = colors.foregroundColor,
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colors.foregroundColor,
        )
    }
}
