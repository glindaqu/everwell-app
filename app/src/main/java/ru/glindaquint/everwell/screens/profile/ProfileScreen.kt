package ru.glindaquint.everwell.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.pxToDp
import ru.glindaquint.everwell.viewModels.impl.ProfileViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val user = viewModel.user.collectAsState()
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }

    EverwellScaffold(
        contentPadding = PaddingValues(0.dp),
        containerColor = MainBackground,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Profile",
                colors =
                    MainTopBarColors(
                        backgroundColor = MainPrimary,
                        foregroundColor = MainSecondary,
                        behindContainerColor = MainSecondary,
                    ),
                onIconClick = {
                    navHostController.navigateUp()
                },
            )
        },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MainSecondary)
                    .padding(15.dp)
                    .onGloballyPositioned {
                        viewSize.value = it.size
                    },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                AsyncImage(
                    model = "https://i.pinimg.com/originals/25/05/6a/25056adc1178c436437713d7444ba8a0.jpg",
                    contentDescription = "User profile image",
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .size((viewSize.value.height * 2 / 3).pxToDp()),
                )
                Text(
                    text = "${user.value?.lastname} ${user.value?.firstname}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "@${user.value?.username}",
                    fontSize = 14.sp,
                    color = MainPrimary,
                    lineHeight = 14.sp,
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Logout",
                        tint = BloodPressurePrimary,
                        modifier = Modifier.scale(scaleX = -1f, scaleY = 1f),
                    )
                }
                IconButton(onClick = { navHostController.navigate(MainRoutes.profileInfo.routeName) }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit profile info",
                    )
                }
            }
        }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 21.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ProfileDataTile(title = "Main data") {
                ProfileDataTileContentItem(title = "Birth date: ", value = "20.02.2001")
                ProfileDataTileContentItem(title = "Sex: ", value = "Male")
            }
            ProfileDataTile(title = "Body metrics") {
                ProfileDataTileContentItem(title = "Weight: ", value = "65kg")
                ProfileDataTileContentItem(title = "Height: ", value = "188cm")
            }
            ProfileDataTile(title = "Sicks") {
                ProfileDataTileContentItem(title = "", value = "None")
            }
            ProfileDataTile(title = "Bad habits") {
                ProfileDataTileContentItem(title = "Smoke: ", value = "None")
                ProfileDataTileContentItem(title = "Alcohol: ", value = "None")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileDataTile(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier =
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .background(MainSecondary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            color = MainPrimary,
            fontSize = 20.sp,
            modifier =
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f),
        )
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MainOnBackground),
        )
        Column(
            modifier =
                Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 7.dp),
        ) {
            content()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileDataTileContentItem(
    title: String,
    value: String,
) {
    Text(
        text =
            buildAnnotatedString {
                append(title)
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                    append(value)
                }
            },
        fontSize = 14.sp,
    )
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun Test_ProfileDataTile() {
    ProfileDataTile(title = "Main data") {
        Text(
            text =
                buildAnnotatedString {
                    append("Birth date: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("26.02.2001")
                    }
                },
            fontSize = 14.sp,
        )
        Text(
            text =
                buildAnnotatedString {
                    append("Sex: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("Male")
                    }
                },
            fontSize = 14.sp,
        )
    }
}
