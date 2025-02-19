package ru.glindaquint.everwell.sharedComponents.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.glindaquint.everwell.dto.colors.navigation.NavigationDrawerColors
import ru.glindaquint.everwell.ui.theme.Typography

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationSheetHeader(navigationDrawerColors: NavigationDrawerColors) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = navigationDrawerColors.headerColor,
                ).padding(start = 16.dp, top = 13.dp, bottom = 8.dp)
                .statusBarsPadding(),
    ) {
        AsyncImage(
            model = "https://i.pinimg.com/originals/25/05/6a/25056adc1178c436437713d7444ba8a0.jpg",
            contentDescription = "User profile image",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(83.dp)
                    .clip(CircleShape),
        )
        Text(
            text = "Jayce Talis",
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = Typography.bodyMedium.fontWeight,
            fontFamily = Typography.bodyMedium.fontFamily,
            modifier = Modifier.padding(top = 5.dp),
        )
        Text(
            text = "@skibidi_hex_jayce",
            fontSize = Typography.labelMedium.fontSize,
            fontWeight = Typography.labelMedium.fontWeight,
            fontFamily = Typography.labelMedium.fontFamily,
            color = navigationDrawerColors.nicknameColor,
            lineHeight = 10.sp,
        )
    }
}
