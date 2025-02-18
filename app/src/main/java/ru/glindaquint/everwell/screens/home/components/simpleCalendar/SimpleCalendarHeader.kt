package ru.glindaquint.everwell.screens.home.components.simpleCalendar

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.uiStates.homeUiState.SimpleCalendarUiState

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SimpleDateFormat")
@Composable
internal fun SimpleCalendarHeader(
    state: SimpleCalendarUiState,
    prevMonthClick: () -> Unit,
    nextMonthClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .padding(top = 12.dp, start = 7.dp)
                .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { prevMonthClick() },
            modifier = Modifier.size(24.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "previous month",
                tint = MainAccent,
            )
        }
        Text(
            text = SimpleDateFormat("MMM yyyy").format(state.getDate()).uppercase(),
            color = MainSecondary,
            fontSize = 14.sp,
        )
        IconButton(
            onClick = { nextMonthClick() },
            modifier = Modifier.size(24.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "next month",
                tint = MainAccent,
            )
        }
    }
}
