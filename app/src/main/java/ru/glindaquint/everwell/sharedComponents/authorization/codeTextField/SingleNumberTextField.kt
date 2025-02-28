package ru.glindaquint.everwell.sharedComponents.authorization.codeTextField

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import ru.glindaquint.everwell.dto.colors.CodeTextFieldColors

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun SingleNumberTextField(
    state: MutableState<String>,
    numberState: MutableState<TextFieldValue>,
    shape: Shape,
    size: Dp,
    enabled: Boolean,
    colors: CodeTextFieldColors,
    focusRequester: MutableState<FocusRequester>,
    targetFocusRequester: MutableState<FocusRequester>?,
) {
    TextField(
        value = numberState.value,
        onValueChange = {
            if (numberState.value.text.isEmpty()) {
                state.value += it.text
                numberState.value = it
                targetFocusRequester?.value?.requestFocus()
            }
        },
        enabled = enabled,
        modifier =
            Modifier
                .size(size)
                .focusRequester(focusRequester.value),
        shape = shape,
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = colors.containerColor,
                unfocusedContainerColor = colors.containerColor,
                disabledContainerColor = colors.disabledContainerColor,
            ),
        textStyle =
            LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = colors.contentColor,
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation('⬤'),
    )
}
