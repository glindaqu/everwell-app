package ru.glindaquint.everwell.screens.authorization.signIn

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.ContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer
import ru.glindaquint.everwell.viewModels.impl.SignInViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel<SignInViewModel>(),
    navHostController: NavHostController,
) {
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
            action = { /*TODO*/ },
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

@RequiresApi(Build.VERSION_CODES.R)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun Authorization_Preview() {
}
