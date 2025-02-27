package ru.glindaquint.everwell.screens.authorization.signIn

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.activities.MainActivity
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.network.dto.authorization.signIn.SignInRequest
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.AuthorizationContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer
import ru.glindaquint.everwell.sharedComponents.labeledTextField.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.passwordTrailingIcon.PasswordTrailingIcon
import ru.glindaquint.everwell.viewModels.impl.SignInViewModel

@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel<SignInViewModel>(),
    navHostController: NavHostController,
) {
    val context = LocalActivity.current as Activity

    val login = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    val canSeePassword = remember { mutableStateOf(false) }

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSavedUser()
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

    AuthorizationContentContainer(topBarTitle = stringResource(id = R.string.authorization_screen_topbar_title)) {
        if (uiState.value.loading) {
            SignInLoadingScreen()
        }
        LabeledTextField(
            state = login,
            labelText = stringResource(id = R.string.authorization_screen_login_title),
        )
        LabeledTextField(
            state = password,
            labelText = stringResource(id = R.string.registration_screen_password_title),
            keyboardType = KeyboardType.Password,
            visualTransformation =
                if (!canSeePassword.value) {
                    PasswordVisualTransformation('•')
                } else {
                    VisualTransformation.None
                },
            trailingIcon = {
                PasswordTrailingIcon(icon = if (canSeePassword.value) R.drawable.visibility_on else R.drawable.visibility_off) {
                    canSeePassword.value = !canSeePassword.value
                }
            },
        )
        ActionButton(
            text = stringResource(id = R.string.authorization_screen_sign_in_text),
            action = {
                viewModel.signIn(
                    SignInRequest(
                        username = login.value.text,
                        password = password.value.text,
                    ),
                )
            },
        )
        OptionsContainer {
            Option(
                text = stringResource(id = R.string.authorization_screen_sign_up_text),
                action = { navHostController.navigate(AuthorizationRoutes.SIGN_UP) },
            )
            Option(
                text = stringResource(id = R.string.authorization_screen_restore_access),
                action = { navHostController.navigate(AuthorizationRoutes.RESTORE) },
            )
        }
    }
}
