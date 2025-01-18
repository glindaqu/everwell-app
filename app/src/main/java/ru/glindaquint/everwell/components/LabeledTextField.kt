package ru.glindaquint.everwell.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun LabeledTextField(
    state: MutableState<TextFieldValue>,
    labelText: String,
    textTransform: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = labelText,
            color = Color.Black,
            fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
            fontSize = 14.sp,
        )
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            colors =
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MainSecondary,
                    unfocusedContainerColor = MainSecondary,
                ),
            visualTransformation = textTransform,
            trailingIcon = {
                trailingIcon?.invoke()
            },
        )
    }
}
