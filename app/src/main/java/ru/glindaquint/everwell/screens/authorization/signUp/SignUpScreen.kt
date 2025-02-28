package ru.glindaquint.everwell.screens.authorization.signUp

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.AuthorizationContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer
import ru.glindaquint.everwell.sharedComponents.authorization.PasswordTrailingIcon

@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun SignUpScreen(navHostController: NavHostController) {
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordAgain = remember { mutableStateOf(TextFieldValue()) }

    val emailFocused = remember { mutableStateOf(false) }

    val canSeePassword = remember { mutableStateOf(false) }
    val canSeePasswordAgain = remember { mutableStateOf(false) }

    val emailError = remember { mutableStateOf(false) }
    val passwordError = remember { mutableStateOf(false) }
    val passwordAgainError = remember { mutableStateOf(false) }

    val emailTitle = remember { mutableStateOf("") }
    val passwordTitle = remember { mutableStateOf("") }
    val passwordAgainTitle = remember { mutableStateOf("") }

    when {
        (
            !email.value.text
                .contains('@') ||
                !email.value.text
                    .contains('.')
        ) &&
            email.value.text
                .isNotEmpty() &&
            !emailFocused.value -> {
            emailError.value = true
            emailTitle.value =
                stringResource(id = R.string.registration_screen_error_email_must_contain)
        }

        else -> {
            emailError.value = false
            emailTitle.value = stringResource(id = R.string.registration_screen_email_title)
        }
    }

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

    AuthorizationContentContainer(topBarTitle = stringResource(id = R.string.registration_screen_topbar_title)) {
        LabeledTextField(
            state = email,
            labelText = emailTitle.value,
            keyboardType = KeyboardType.Email,
            isError = emailError.value,
            modifier =
                Modifier.onFocusChanged {
                    emailFocused.value = it.isFocused
                },
        )
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
        ActionButton(
            text = stringResource(id = R.string.registration_screen_sign_up_text),
            action = {
                navHostController.navigate(
                    "${AuthorizationRoutes.CONFIRM_EMAIL}/${
                        SignUpRequest(
                            username =
                                email.value.text
                                    .split('@')[0],
                            email = email.value.text,
                            password = email.value.text,
                        ).toJson()
                    }",
                )
            },
            enabled =
                email.value.text.contains('@') &&
                    email.value.text.contains('.') &&
                    password.value.text.length >= 8 &&
                    passwordAgain.value.text.length >= 8 &&
                    passwordAgain.value.text == password.value.text,
        )
        OptionsContainer {
            Option(
                text = stringResource(id = R.string.registration_screen_sign_in_text),
                action = { navHostController.navigate(AuthorizationRoutes.SIGN_IN) },
            )
            Option(
                text = stringResource(id = R.string.registration_screen_restore_access),
                action = { navHostController.navigate(AuthorizationRoutes.RESTORE) },
            )
        }
    }
}
