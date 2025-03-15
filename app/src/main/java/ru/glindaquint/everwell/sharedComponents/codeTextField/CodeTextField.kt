package ru.glindaquint.everwell.sharedComponents.codeTextField

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.dto.colors.CodeTextFieldColors
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
    verifyInput: () -> Boolean = { false },
) {
    val firstFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val secondFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val thirdFocusRequester = remember { mutableStateOf(FocusRequester()) }
    val fourthFocusRequester = remember { mutableStateOf(FocusRequester()) }

    val firstNumberState = remember { mutableStateOf(TextFieldValue()) }
    val secondNumberState = remember { mutableStateOf(TextFieldValue()) }
    val thirdNumberState = remember { mutableStateOf(TextFieldValue()) }
    val fourthNumberState = remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current

    val offsetAnimationState = remember { Animatable(0f) }

    val isError = remember { mutableStateOf(false) }

    LaunchedEffect(state.value) {
        if (state.value.length == 4) {
            isError.value = !verifyInput()
        }
    }

    LaunchedEffect(offsetAnimationState.value) {
        if (offsetAnimationState.targetValue == 0f && offsetAnimationState.value == 0f) {
            isError.value = false
        }
    }

    LaunchedEffect(isError.value) {
        if (isError.value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                vibrateOnError(context)
            }

            state.value = ""

            firstNumberState.value = TextFieldValue()
            secondNumberState.value = TextFieldValue()
            thirdNumberState.value = TextFieldValue()
            fourthNumberState.value = TextFieldValue()

            firstFocusRequester.value.requestFocus()
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = stringResource(id = R.string.restore_screen_code_title),
            color = Color.Black,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            modifier =
                Modifier
                    .shakeAnimation(
                        state = offsetAnimationState,
                        isError = isError.value,
                    ).fillMaxWidth(),
        ) {
            SingleNumberTextField(
                state = state,
                shape = RoundedCornerShape(bottomStart = 10.dp, topStart = 10.dp),
                size = size,
                focusRequester = firstFocusRequester,
                targetFocusRequester = secondFocusRequester,
                enabled = enabled,
                colors = colors,
                numberState = firstNumberState,
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = secondFocusRequester,
                targetFocusRequester = thirdFocusRequester,
                enabled = enabled,
                colors = colors,
                numberState = secondNumberState,
            )
            SingleNumberTextField(
                state = state,
                shape = RectangleShape,
                size = size,
                focusRequester = thirdFocusRequester,
                targetFocusRequester = fourthFocusRequester,
                enabled = enabled,
                colors = colors,
                numberState = thirdNumberState,
            )
            SingleNumberTextField(
                state = state,
                shape = RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp),
                size = size,
                focusRequester = fourthFocusRequester,
                targetFocusRequester = null,
                enabled = enabled,
                colors = colors,
                numberState = fourthNumberState,
            )
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload", "ModifierFactoryUnreferencedReceiver")
@Suppress("ktlint:standard:function-naming")
@Composable
private fun Modifier.shakeAnimation(
    state: Animatable<Float, AnimationVector1D>,
    isError: Boolean,
): Modifier {
    LaunchedEffect(isError) {
        if (isError) {
            state.animateTo(
                targetValue = 10f,
                animationSpec =
                    repeatable(
                        iterations = 5,
                        animation = tween(durationMillis = 100, easing = FastOutSlowInEasing),
                    ),
            )
            state.animateTo(0f)
        }
    }

    return Modifier.offset(x = state.value.dp)
}

@RequiresApi(Build.VERSION_CODES.S)
private fun vibrateOnError(context: Context) {
    val vibratorManager =
        context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    val vibrator = vibratorManager.defaultVibrator
    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
}
