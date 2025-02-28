package ru.glindaquint.everwell.screens.authorization.signUp

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.activities.MainActivity
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.AuthorizationContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.codeTextField.CodeTextField
import ru.glindaquint.everwell.viewModels.impl.SignUpViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ConfirmEmailScreen(request: SignUpRequest) {
    val codeTextFieldState = remember { mutableStateOf("") }
    val viewModel = hiltViewModel<SignUpViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalActivity.current as Activity

    LaunchedEffect(Unit) {
        viewModel.sendCode(request.email)
    }

    LaunchedEffect(uiState.value) {
        if (uiState.value.successful) {
            context.startActivity(
                Intent(context, MainActivity::class.java),
                null,
            )
            context.finish()
        }
    }

    AuthorizationContentContainer(topBarTitle = stringResource(id = R.string.registration_screen_topbar_title)) {
        CodeTextField(state = codeTextFieldState)
        ActionButton(
            text = stringResource(id = R.string.restore_screen_restore_text),
            action = {
                if (codeTextFieldState.value == uiState.value.code) {
                    viewModel.signUp(request = request)
                }
            },
        )
    }
}
