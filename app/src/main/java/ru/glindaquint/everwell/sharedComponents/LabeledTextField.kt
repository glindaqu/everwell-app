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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainContainerError
import ru.glindaquint.everwell.ui.theme.MainSecondary

@SuppressLint("UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    labelText: String,
    textTransform: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    errorHandler: ((MutableState<Int?>, MutableState<Boolean>) -> Unit)? = null,
) {
    val focusState = remember { mutableStateOf(false) }
    val errorMessageResource = remember { mutableStateOf<Int?>(null) }
    val error =
        derivedStateOf {
            when {
                errorMessageResource.value == null -> false
                else -> true
            }
        }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text =
                if (errorMessageResource.value != null) {
                    "$labelText (${stringResource(id = errorMessageResource.value!!)})"
                } else {
                    labelText
                },
            color = if (errorMessageResource.value == null) Color.Black else MainContainerError,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                errorHandler?.invoke(errorMessageResource, focusState)
            },
            shape = RoundedCornerShape(10.dp),
            modifier =
                modifier.fillMaxWidth().onFocusChanged {
                    focusState.value = it.isFocused
                    errorHandler?.invoke(errorMessageResource, focusState)
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
