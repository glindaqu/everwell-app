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
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.sharedComponents.authorization.ActionButton
import ru.glindaquint.everwell.sharedComponents.authorization.ContentContainer
import ru.glindaquint.everwell.sharedComponents.labeledTextField.LabeledTextField

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun NewPasswordScreen() {
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordAgain = remember { mutableStateOf(TextFieldValue()) }

    val canSeePassword = remember { mutableStateOf(false) }
    val canSeePasswordAgain = remember { mutableStateOf(false) }

    ContentContainer(topBarTitle = stringResource(id = R.string.restore_screen_topbar_title)) {
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
        )
        LabeledTextField(
            state = passwordAgain,
            labelText = stringResource(id = R.string.registration_screen_password_again_title),
            keyboardType = KeyboardType.Password,
            visualTransformation =
                if (!canSeePasswordAgain.value) {
                    PasswordVisualTransformation('•')
                } else {
                    VisualTransformation.None
                },
        )
        ActionButton(
            text = stringResource(id = R.string.registration_screen_sign_up_text),
            action = { /*TODO*/ },
        )
    }
}
