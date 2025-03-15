package ru.glindaquint.everwell.sharedComponents

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Suppress("ktlint:standard:function-naming")
@Composable
fun PasswordTrailingIcon(
    icon: Int,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Hide/Display password",
        )
    }
}
