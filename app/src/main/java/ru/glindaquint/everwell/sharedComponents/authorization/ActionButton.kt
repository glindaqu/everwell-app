package ru.glindaquint.everwell.sharedComponents.authorization

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun ActionButton(
    text: String,
    action: () -> Unit,
) {
    Button(
        onClick = { action() },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 33.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MainPrimary),
    ) {
        Text(
            text = text,
            fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.padding(vertical = 10.dp),
            color = MainSecondary,
        )
    }
}
