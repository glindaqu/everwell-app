package ru.glindaquint.everwell.screens.authorization.restore

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.navigation.authorization.AuthorizationRoutes
import ru.glindaquint.everwell.screens.authorization.restore.components.CodeTextField
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.ContentContainer
import ru.glindaquint.everwell.sharedComponents.authorization.Option
import ru.glindaquint.everwell.sharedComponents.authorization.OptionsContainer
import ru.glindaquint.everwell.utils.pxToDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("ktlint:standard:function-naming")
@Composable
fun RestoreScreen(navHostController: NavHostController) {
    val emailTextFieldState = remember { mutableStateOf(TextFieldValue()) }
    val codeTextFieldState = remember { mutableStateOf("") }
    val textFieldHeight = remember { mutableStateOf(0.dp) }

    ContentContainer(topBarTitle = stringResource(id = R.string.restore_screen_topbar_title)) {
        LabeledTextField(
            modifier =
                Modifier.onGloballyPositioned { coordinates ->
                    textFieldHeight.value = coordinates.size.height.pxToDp()
                },
            state = emailTextFieldState,
            labelText = stringResource(id = R.string.restore_screen_email_title),
            errorHandler = { errorMessage, isFocused ->
                if (emailTextFieldState.value.text.isEmpty()) {
                    errorMessage.value = null
                } else if (isFocused.value) {
                    errorMessage.value = null
                } else if (!emailTextFieldState.value.text.contains('@')) {
                    errorMessage.value = R.string.registration_screen_error_email_must_contain
                } else if (!emailTextFieldState.value.text.contains('.')) {
                    errorMessage.value = R.string.registration_screen_error_email_must_contain
                }
            },
            keyboardType = KeyboardType.Email,
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
