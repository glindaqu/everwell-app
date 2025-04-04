package ru.glindaquint.everwell.sharedComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary

@Suppress("ktlint:standard:function-naming")
@Composable
fun EverwellActionButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = MainPrimary,
    foregroundColor: Color = MainSecondary,
    paddingValues: PaddingValues = PaddingValues(top = 33.dp),
    action: () -> Unit,
) {
    Button(
        onClick = { action() },
        modifier =
            Modifier
                .fillMaxWidth()
                .then(modifier)
                .padding(paddingValues),
        shape = RoundedCornerShape(12.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        Text(
            text = text,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            modifier = Modifier.padding(vertical = 10.dp),
            color = foregroundColor,
        )
    }
}
