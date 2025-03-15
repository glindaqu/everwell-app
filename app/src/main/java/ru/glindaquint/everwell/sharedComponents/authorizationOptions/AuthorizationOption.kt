package ru.glindaquint.everwell.sharedComponents.authorizationOptions

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.glindaquint.everwell.ui.theme.MainAccent

@Suppress("ktlint:standard:function-naming")
@Composable
fun AuthorizationOption(
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
        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
    )
}
