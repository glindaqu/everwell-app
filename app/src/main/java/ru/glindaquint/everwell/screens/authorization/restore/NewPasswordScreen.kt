package ru.glindaquint.everwell.screens.authorization.restore

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
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
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.network.dto.authorization.RestoreRequest
import ru.glindaquint.everwell.sharedComponents.AuthorizationContentContainer
import ru.glindaquint.everwell.sharedComponents.EverwellActionButton
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.PasswordTrailingIcon
import ru.glindaquint.everwell.viewModels.impl.RestoreViewModel

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun NewPasswordScreen(
    email: String?,
    navHostController: NavHostController,
) {
    val viewModel = hiltViewModel<RestoreViewModel>()

    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordAgain = remember { mutableStateOf(TextFieldValue()) }

    val canSeePassword = remember { mutableStateOf(false) }
    val canSeePasswordAgain = remember { mutableStateOf(false) }

    val passwordError = remember { mutableStateOf(false) }
    val passwordAgainError = remember { mutableStateOf(false) }

    val passwordTitle = remember { mutableStateOf("") }
    val passwordAgainTitle = remember { mutableStateOf("") }

    when {
        password.value.text.length < 8 &&
            password.value.text
                .isNotEmpty() -> {
            passwordError.value = true
            passwordTitle.value =
                stringResource(id = R.string.registration_screen_error_password_too_short)
        }

        password.value.text != passwordAgain.value.text &&
            password.value.text
                .isNotEmpty() &&
            passwordAgain.value.text
                .isNotEmpty() -> {
            passwordError.value = true
            passwordTitle.value =
                stringResource(id = R.string.registration_screen_error_passwords_are_differ)
        }

        else -> {
            passwordError.value = false
            passwordTitle.value = stringResource(id = R.string.registration_screen_password_title)
        }
    }

    when {
        passwordAgain.value.text.length < 8 &&
            passwordAgain.value.text
                .isNotEmpty() -> {
            passwordAgainError.value = true
            passwordAgainTitle.value =
                stringResource(id = R.string.registration_screen_error_password_too_short)
        }

        password.value.text != passwordAgain.value.text &&
            password.value.text
                .isNotEmpty() &&
            passwordAgain.value.text
                .isNotEmpty() -> {
            passwordAgainError.value = true
            passwordAgainTitle.value =
                stringResource(id = R.string.registration_screen_error_passwords_are_differ)
        }

        else -> {
            passwordAgainError.value = false
            passwordAgainTitle.value =
                stringResource(id = R.string.registration_screen_password_again_title)
        }
    }

    AuthorizationContentContainer(topBarTitle = stringResource(id = R.string.restore_screen_topbar_title)) {
        LabeledTextField(
            state = password,
            labelText = passwordTitle.value,
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
            isError = passwordError.value,
        )
        LabeledTextField(
            state = passwordAgain,
            labelText = passwordAgainTitle.value,
            keyboardType = KeyboardType.Password,
            visualTransformation =
                if (!canSeePasswordAgain.value) {
                    PasswordVisualTransformation('•')
                } else {
                    VisualTransformation.None
                },
            trailingIcon = {
                PasswordTrailingIcon(icon = if (canSeePasswordAgain.value) R.drawable.visibility_on else R.drawable.visibility_off) {
                    canSeePasswordAgain.value = !canSeePasswordAgain.value
                }
            },
            isError = passwordAgainError.value,
        )
        EverwellActionButton(
            text = stringResource(id = R.string.registration_screen_sign_up_text),
            action = {
                viewModel.resetPassword(
                    request = RestoreRequest(password = password.value.text, email = email!!),
                    callback = {
                        navHostController.navigate(AuthorizationRoutes.SIGN_IN)
                    },
                )
            },
            enabled =
                password.value.text.length >= 8 &&
                    passwordAgain.value.text.length >= 8 &&
                    passwordAgain.value.text == password.value.text,
        )
    }
}
