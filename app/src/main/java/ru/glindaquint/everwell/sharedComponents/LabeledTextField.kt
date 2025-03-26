package ru.glindaquint.everwell.sharedComponents

import android.annotation.SuppressLint
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.dto.colors.LabeledTextFieldColors
import ru.glindaquint.everwell.ui.theme.MainContainerError
import ru.glindaquint.everwell.ui.theme.MainSecondary

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    colors: LabeledTextFieldColors =
        LabeledTextFieldColors(
            containerColor = MainSecondary,
            contentColor = Color.Black,
            errorContainerColor = MainContainerError,
            errorContentColor = MainContainerError,
        ),
    enabled: Boolean = true,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
    state: MutableState<TextFieldValue>,
    labelText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = labelText,
            color = if (isError) colors.errorContentColor else colors.contentColor,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        TextField(
            value = state.value.text,
            onValueChange = {
                state.value = state.value.copy(text = it)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.fillMaxWidth(),
            colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = colors.containerColor,
                    unfocusedContainerColor = colors.containerColor,
                    errorContainerColor = colors.errorContainerColor,
                    errorIndicatorColor = Color.Transparent,
                    disabledTextColor = colors.contentColor,
                    disabledContainerColor = colors.containerColor,
                ),
            visualTransformation = visualTransformation,
            trailingIcon = {
                trailingIcon?.invoke()
            },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            enabled = enabled,
        )
    }
}
