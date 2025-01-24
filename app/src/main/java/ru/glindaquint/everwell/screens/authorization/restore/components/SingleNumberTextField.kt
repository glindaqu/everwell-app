package ru.glindaquint.everwell.screens.authorization.restore.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun SingleNumberTextField(
    state: MutableState<String>,
    shape: Shape,
    size: Dp,
    focusRequester: MutableState<FocusRequester>,
    targetFocusRequester: MutableState<FocusRequester>?,
) {
    val numberState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = numberState.value,
        onValueChange = {
            state.value += it.text
            numberState.value = it
            targetFocusRequester?.value?.requestFocus()
        },
        modifier = Modifier.size(size).focusRequester(focusRequester.value),
        shape = shape,
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = MainSecondary,
                unfocusedContainerColor = MainSecondary,
            ),
        textStyle =
            LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = MainBackground,
            ),
        visualTransformation = PasswordVisualTransformation('⬤'),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}
