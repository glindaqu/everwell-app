package ru.glindaquint.everwell.sharedComponents.authorization

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.glindaquint.everwell.ui.theme.MainAccent

@Suppress("ktlint:standard:function-naming")
@Composable
fun Option(
    text: String,
    action: () -> Unit,
) {
    Text(
        modifier =
            Modifier.clickable {
                action()
            },
        text = text,
        color = MainAccent,
        fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
    )
}
