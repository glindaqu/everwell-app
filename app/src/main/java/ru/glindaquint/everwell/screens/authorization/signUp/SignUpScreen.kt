package ru.glindaquint.everwell.screens.authorization.signUp

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.ContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer

@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navHostController: NavHostController) {
    val emailTextFieldState = remember { mutableStateOf(TextFieldValue()) }
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
            state = emailTextFieldState,
            labelText = stringResource(id = R.string.registration_screen_login_title),
            keyboardType = KeyboardType.Email,
            errorHandler = { textFieldValue, isErrorState, isFocused ->
                if (isFocused || textFieldValue.text.isEmpty()) {
                    isErrorState.value = false
                } else {
                    isErrorState.value =
                        !textFieldValue.text.contains('@') ||
                        !textFieldValue.text.contains('.')
                }
            },
        )
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
            errorHandler = { password, isError, isFocused ->
                isError.value = (password.text.length < 8 && password.text.isNotEmpty()) ||
                    !isFocused &&
                    passwordTextFieldState.value.text != passwordAgainTextFieldState.value.text
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
            errorHandler = { password, isError, isFocused ->
                isError.value = (password.text.length < 8 && password.text.isNotEmpty()) ||
                    !isFocused &&
                    passwordTextFieldState.value.text != passwordAgainTextFieldState.value.text
            },
        )
        ActionButton(
            text = stringResource(id = R.string.registration_screen_sign_up_text),
            action = { /*TODO*/ },
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

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun Registration_Preview() {
}
