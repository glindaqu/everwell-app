package ru.glindaquint.everwell.screens.authorization.restore

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.screens.authorization.restore.components.CodeTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.AuthorizationContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer
import ru.glindaquint.everwell.sharedComponents.labeledTextField.LabeledTextField
import ru.glindaquint.everwell.utils.pxToDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun RestoreScreen(navHostController: NavHostController) {
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailFocused = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }
    val emailTitle = remember { mutableStateOf("") }

    val codeTextFieldState = remember { mutableStateOf("") }
    val textFieldHeight = remember { mutableStateOf(0.dp) }

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

    AuthorizationContentContainer(topBarTitle = stringResource(id = R.string.restore_screen_topbar_title)) {
        LabeledTextField(
            state = email,
            labelText = emailTitle.value,
            keyboardType = KeyboardType.Email,
            modifier =
                Modifier
                    .onGloballyPositioned {
                        textFieldHeight.value = it.size.height.pxToDp()
                    }.onFocusChanged {
                        emailFocused.value = it.isFocused
                    },
            isError = emailError.value,
        )
        CodeTextField(size = textFieldHeight.value, state = codeTextFieldState)
        ActionButton(
            text = stringResource(id = R.string.restore_screen_restore_text),
            action = { /*TODO*/ },
        )
        OptionsContainer {
            Option(text = stringResource(id = R.string.restore_screen_sign_in_text), action = {
                navHostController.navigate(AuthorizationRoutes.SIGN_IN)
            })
        }
    }
}
