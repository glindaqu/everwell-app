package ru.glindaquint.everwell.sharedComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainContainerError
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    labelText: String,
    textTransform: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    errorHandler: ((MutableState<Boolean>, MutableState<Boolean>) -> Unit)? = null,
) {
    val error = remember { mutableStateOf(false) }
    val focusState = remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = labelText,
            color = Color.Black,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                errorHandler?.invoke(error, focusState)
            },
            shape = RoundedCornerShape(10.dp),
            modifier =
                modifier.fillMaxWidth().onFocusChanged {
                    focusState.value = it.isFocused
                    errorHandler?.invoke(error, focusState)
                },
            colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MainSecondary,
                    unfocusedContainerColor = MainSecondary,
                    errorContainerColor = MainContainerError,
                    errorIndicatorColor = Color.Transparent,
                ),
            visualTransformation = textTransform,
            trailingIcon = {
                trailingIcon?.invoke()
            },
            isError = error.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        )
    }
}
