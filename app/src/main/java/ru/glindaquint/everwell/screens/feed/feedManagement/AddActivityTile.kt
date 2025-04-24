package ru.glindaquint.everwell.screens.feed.feedManagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun AddActivityTile(
    backgroundColor: Color,
    spacerColor: Color,
    icon: Painter,
    title: String,
    content: String,
    placeholder: String,
    readonly: Boolean = false,
    onClick: () -> Unit,
) {
    val buttonSize = remember { mutableStateOf(IntSize(0, 0)) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = icon, contentDescription = "Tile icon", tint = FeedSecondary)
            Text(text = title, color = FeedSecondary, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(spacerColor))
        Row(
            modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (content.isEmpty()) {
                Text(
                    text = if (readonly) "Nothing to display" else placeholder,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .padding(start = buttonSize.value.width.pxToDp()),
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(text = content)
            }
            IconButton(
                onClick = { onClick() },
                modifier =
                    Modifier.wrapContentSize().onGloballyPositioned {
                        buttonSize.value = it.size
                    },
                enabled = !readonly,
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add new activity",
                    tint = FeedPrimary.copy(if (readonly) 0.3f else 1f),
                    modifier = Modifier.fillMaxSize(0.75f),
                )
            }
        }
    }
}
