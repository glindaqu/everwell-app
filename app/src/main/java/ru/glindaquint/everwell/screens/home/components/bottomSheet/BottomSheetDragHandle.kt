package ru.glindaquint.everwell.screens.home.components.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun BottomSheetDragHandle() {
    Spacer(
        modifier =
            Modifier
                .padding(vertical = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .width(100.dp)
                .height(3.dp)
                .background(MainPrimary),
    )
}
