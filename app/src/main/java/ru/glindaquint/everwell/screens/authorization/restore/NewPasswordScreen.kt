package ru.glindaquint.everwell.screens.authorization.restore

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.ContentContainer

@Suppress("ktlint:standard:function-naming")
@Composable
fun NewPasswordScreen() {
    val passwordTextFieldState = remember { mutableStateOf(TextFieldValue()) }
    val passwordAgainTextFieldState = remember { mutableStateOf(TextFieldValue()) }

    val canSeePassword = remember { mutableStateOf(false) }
    val canSeePasswordAgain = remember { mutableStateOf(false) }

    val passwordTextTransform =
        remember {
            derivedStateOf {
                if (!canSeePassword.value) {
                    PasswordVisualTransformation('•')
                } else {
                    VisualTransformation.None
                }
            }
        }
    val passwordAgainTextTransform =
        remember {
            derivedStateOf {
                if (!canSeePasswordAgain.value) {
                    PasswordVisualTransformation('•')
                } else {
                    VisualTransformation.None
                }
            }
        }

    val passwordTrailingIcon =
        remember {
            derivedStateOf {
                if (canSeePassword.value) {
                    R.drawable.visibility_on
                } else {
                    R.drawable.visibility_off
                }
            }
        }
    val passwordAgainTrailingIcon =
        remember {
            derivedStateOf {
                if (canSeePasswordAgain.value) {
                    R.drawable.visibility_on
                } else {
                    R.drawable.visibility_off
                }
            }
        }

    ContentContainer(topBarTitle = stringResource(id = R.string.restore_screen_topbar_title)) {
        LabeledTextField(
            state = passwordTextFieldState,
            labelText = stringResource(id = R.string.registration_screen_password_title),
            textTransform = passwordTextTransform.value,
            trailingIcon = {
                IconButton(onClick = { canSeePassword.value = !canSeePassword.value }) {
                    Icon(
                        painter = painterResource(id = passwordTrailingIcon.value),
                        contentDescription = "Hide/Display password",
                    )
                }
            },
            keyboardType = KeyboardType.Password,
            errorHandler = { errorMessage, _ ->
                if (passwordTextFieldState.value.text.isEmpty() || passwordAgainTextFieldState.value.text.isEmpty()) {
                    errorMessage.value = null
                } else if (passwordTextFieldState.value.text != passwordAgainTextFieldState.value.text) {
                    errorMessage.value = R.string.registration_screen_error_passwords_are_differ
                } else if (passwordTextFieldState.value.text.length < 8) {
                    errorMessage.value = R.string.registration_screen_error_password_too_short
                } else {
                    errorMessage.value = null
                }
            },
        )
        LabeledTextField(
            state = passwordAgainTextFieldState,
            labelText = stringResource(id = R.string.registration_screen_password_again_title),
            textTransform = passwordAgainTextTransform.value,
            trailingIcon = {
                IconButton(onClick = {
                    canSeePasswordAgain.value = !canSeePasswordAgain.value
                }) {
                    Icon(
                        painter = painterResource(id = passwordAgainTrailingIcon.value),
                        contentDescription = "Hide/Display password (again)",
                    )
                }
            },
            keyboardType = KeyboardType.Password,
            errorHandler = { errorMessage, _ ->
                if (passwordTextFieldState.value.text.isEmpty() || passwordAgainTextFieldState.value.text.isEmpty()) {
                    errorMessage.value = null
                } else if (passwordTextFieldState.value.text != passwordAgainTextFieldState.value.text) {
                    errorMessage.value = R.string.registration_screen_error_passwords_are_differ
                } else if (passwordTextFieldState.value.text.length < 8) {
                    errorMessage.value = R.string.registration_screen_error_password_too_short
                } else {
                    errorMessage.value = null
                }
            },
        )
        ActionButton(
            text = stringResource(id = R.string.registration_screen_sign_up_text),
            action = { /*TODO*/ },
        )
    }
}
