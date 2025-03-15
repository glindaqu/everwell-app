package ru.glindaquint.everwell.sharedComponents.sliderDatePicker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import ru.glindaquint.everwell.R
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Suppress("ktlint:standard:function-naming")
@Composable
fun SliderDatePickerMonth(
    state: SliderDatePickerState,
    colors: SliderDatePickerColors,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { state.decrementMonth() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "prev month",
                tint = colors.actionsColor,
            )
        }
        Text(text = SimpleDateFormat("MMMM yyyy").format(state.date.value), color = colors.monthColor)
        IconButton(onClick = { state.incrementMonth() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "next month",
                tint = colors.actionsColor,
            )
        }
    }
}
