package ru.glindaquint.everwell.screens.familyGroup

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.copyToClipboard
import ru.glindaquint.everwell.viewModels.impl.FamilyGroupViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun FamilyGroupScreen(
    referredFamilyGroupId: Long? = null,
    drawerState: DrawerState,
    navHostController: NavHostController,
    viewModel: FamilyGroupViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.members()
        viewModel.invite()
        Log.d("tag", "FamilyGroupScreen: $referredFamilyGroupId")
    }

    EverwellScaffold(
        containerColor = MainBackground,
        contentPadding = PaddingValues(10.dp),
        topBar = {
            MainTopBar(
                icon = Icons.Default.Menu,
                title = "Family group",
                colors =
                    MainTopBarColors(
                        backgroundColor = MainPrimary,
                        foregroundColor = MainSecondary,
                        behindContainerColor = MainBackground,
                    ),
                onIconClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
    ) {
        when (uiState.value.members.size) {
            0 -> EmptyGroupPlaceholder(uiState.value.inviteLink ?: "")
            else -> {}
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun EmptyGroupPlaceholder(link: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(text = "В данный момент вы не в группе... Скопируйте ссылки или перейдите по " +
                        "ссылке члена семьи!")
            Button(onClick = {
                copyToClipboard(context, "${RetrofitFactory.API_BASE_URL}$link")
            }) {
                Text(text = "Копировать ссылку")
            }
        }
    }
}
