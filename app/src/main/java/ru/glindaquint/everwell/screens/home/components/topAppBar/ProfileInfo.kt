package ru.glindaquint.everwell.screens.home.components.topAppBar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.sharedComponents.UserImage
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.Typography
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileInfo(
    username: String,
    userImage: String?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 19.pxToDp()).clip(RoundedCornerShape(12.dp)),
    ) {
        UserImage(
            image = userImage,
            modifier =
                Modifier.size(43.dp).clip(CircleShape).border(
                    width = 1.dp,
                    color = MainAccent,
                    shape = CircleShape,
                ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.pxToDp()),
        ) {
            Text(
                text = username,
                fontWeight = FontWeight.Medium,
                fontFamily = Typography.bodySmall.fontFamily,
                fontSize = Typography.bodySmall.fontSize,
                color = Color.Black,
            )
        }
    }
}
