package ru.glindaquint.everwell.screens.authorization.restore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

@Suppress("ktlint:standard:function-naming")
@Composable
fun CodeTextField(
    state: MutableState<String>,
    size: Dp,
) {
    val firstFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val secondFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val thirdFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val fourthFocusRequester = remember { mutableStateOf(FocusRequester()) }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = stringResource(id = R.string.restore_screen_code_title),
            color = Color.Black,
            fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
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
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = secondFocusRequester,
                targetFocusRequester = thirdFocusRequester,
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = thirdFocusRequester,
                targetFocusRequester = fourthFocusRequester,
            )
            SingleNumberTextField(
                state = state,
                shape = RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp),
                size = size,
                focusRequester = fourthFocusRequester,
                targetFocusRequester = null,
            )
        }
    }
}
