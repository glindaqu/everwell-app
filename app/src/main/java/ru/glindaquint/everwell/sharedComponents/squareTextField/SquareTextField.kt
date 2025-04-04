package ru.glindaquint.everwell.sharedComponents.squareTextField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.dto.colors.SquareTextFieldColors
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.SquareTextField(
    title: String,
    subtitle: String,
    value: TextFieldValue,
    showMeasurementUnits: Boolean,
    colors: SquareTextFieldColors,
    onValueChanged: (TextFieldValue) -> Unit,
) {
    // 40 / viewSize = x / changedViewSize
    // x = 40 * changedViewSize / viewSize
    val viewWidth = remember { mutableStateOf(1.dp) }
    val currentViewWidth = remember { mutableStateOf(1.dp) }

    Column(
        modifier =
            Modifier
                .weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        if (viewWidth.value == 1.dp) {
                            viewWidth.value = it.size.width.pxToDp()
                        }
                        currentViewWidth.value = it.size.width.pxToDp()
                    }.height(currentViewWidth.value)
                    .background(
                        color = colors.backgroundColor,
                        shape = RoundedCornerShape(12.dp),
                    ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = value,
                singleLine = true,
                onValueChange = {
                    if (it.text.length <= 3) {
                        onValueChanged(it)
                    }
                },
                textStyle =
                    TextStyle(
                        fontSize =
                            (
                                30 * currentViewWidth.value.value.toInt() /
                                    viewWidth.value
                                        .value
                                        .toInt()
                            ).sp,
                        color = colors.contentColor,
                        textAlign = TextAlign.Center,
                    ),
                label = {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors =
                    TextFieldDefaults.colors(
                        focusedContainerColor = colors.backgroundColor,
                        unfocusedContainerColor = colors.backgroundColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = colors.focusedLabelColor,
                        unfocusedLabelColor = colors.unfocusedLabelColor,
                        cursorColor = colors.cursorColor,
                        selectionColors =
                            TextSelectionColors(
                                handleColor = colors.pointerColor,
                                backgroundColor = colors.backgroundColor.copy(0.5f),
                            ),
                    ),
                shape = RoundedCornerShape(12.dp),
            )
            if (showMeasurementUnits) {
                Text(text = subtitle, fontSize = 12.sp, color = BloodPressureAccent)
            }
        }
    }
}
