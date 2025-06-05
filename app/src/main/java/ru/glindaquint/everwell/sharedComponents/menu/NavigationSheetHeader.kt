package ru.glindaquint.everwell.sharedComponents.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.dto.colors.navigation.NavigationDrawerColors
import ru.glindaquint.everwell.sharedComponents.UserImage
import ru.glindaquint.everwell.ui.theme.Typography
import ru.glindaquint.everwell.viewModels.impl.HomeNavigationViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Suppress("ktlint:standard:function-naming")
@Composable
fun NavigationSheetHeader(navigationDrawerColors: NavigationDrawerColors) {
    val viewModel = hiltViewModel<HomeNavigationViewModel>()
    val user = viewModel.user.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = navigationDrawerColors.headerColor,
                ).padding(start = 16.dp, top = 13.dp, bottom = 8.dp)
                .statusBarsPadding(),
    ) {
        UserImage(
            user.value?.image,
            modifier = Modifier.size(83.dp).clip(CircleShape),
        )
        Text(
            text = user.value?.username.toString(),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = Typography.bodyMedium.fontWeight,
            fontFamily = Typography.bodyMedium.fontFamily,
            modifier = Modifier.padding(top = 5.dp),
        )
        Text(
            text = "@${user.value?.username}",
            fontSize = Typography.labelMedium.fontSize,
            fontWeight = Typography.labelMedium.fontWeight,
            fontFamily = Typography.labelMedium.fontFamily,
            color = navigationDrawerColors.nicknameColor,
            lineHeight = 10.sp,
        )
    }
}
