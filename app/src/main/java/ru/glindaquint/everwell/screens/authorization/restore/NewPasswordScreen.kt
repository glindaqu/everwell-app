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
    val loginTextFieldState = remember { mutableStateOf(TextFieldValue()) }
    val passwordTextFieldState = remember { mutableStateOf(TextFieldValue()) }
    val canSeePassword = remember { mutableStateOf(false) }

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

    ContentContainer(topBarTitle = stringResource(id = R.string.authorization_screen_topbar_title)) {
        LabeledTextField(
            state = loginTextFieldState,
            labelText = stringResource(id = R.string.authorization_screen_login_title),
        )
        LabeledTextField(
            state = passwordTextFieldState,
            labelText = stringResource(id = R.string.authorization_screen_password_title),
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
        )
        ActionButton(
            text = stringResource(id = R.string.authorization_screen_sign_in_text),
            action = {
            },
        )
    }
}
