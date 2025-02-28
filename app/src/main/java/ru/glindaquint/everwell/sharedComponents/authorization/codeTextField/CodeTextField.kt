package ru.glindaquint.everwell.sharedComponents.authorization.codeTextField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.dto.colors.codeTextField.CodeTextFieldColors
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun CodeTextField(
    state: MutableState<String>,
    enabled: Boolean = true,
    size: Dp = TextFieldDefaults.MinHeight,
    colors: CodeTextFieldColors =
        CodeTextFieldColors(
            containerColor = MainSecondary,
            contentColor = MainOnBackground,
            disabledContentColor = MainOnBackground,
            disabledContainerColor = MainSecondary.copy(0.3f),
        ),
) {
    val firstFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val secondFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val thirdFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val fourthFocusRequester = remember { mutableStateOf(FocusRequester()) }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = stringResource(id = R.string.restore_screen_code_title),
            color = Color.Black,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            SingleNumberTextField(
                state = state,
                shape = RoundedCornerShape(bottomStart = 10.dp, topStart = 10.dp),
                size = size,
                focusRequester = firstFocusRequester,
                targetFocusRequester = secondFocusRequester,
                enabled = enabled,
                colors = colors,
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = secondFocusRequester,
                targetFocusRequester = thirdFocusRequester,
                enabled = enabled,
                colors = colors,
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = thirdFocusRequester,
                targetFocusRequester = fourthFocusRequester,
                enabled = enabled,
                colors = colors,
            )
            SingleNumberTextField(
                state = state,
                shape = RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp),
                size = size,
                focusRequester = fourthFocusRequester,
                targetFocusRequester = null,
                enabled = enabled,
                colors = colors,
            )
        }
    }
}
