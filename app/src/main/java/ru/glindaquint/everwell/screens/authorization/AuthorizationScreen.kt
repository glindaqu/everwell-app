package ru.glindaquint.everwell.screens.authorization

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.components.LabeledTextField
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.SystemBarsUtils
import ru.glindaquint.everwell.utils.dropShadow
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthorizationScreen() {
    val loginTextFieldState = remember { mutableStateOf(TextFieldValue()) }
    val passwordTextFieldState = remember { mutableStateOf(TextFieldValue()) }

    val visibilityOffResource = R.drawable.visibility_off
    val visibilityOnResource = R.drawable.visibility_on

    val canSeePassword = remember { mutableStateOf(false) }

    val passwordTextTransform =
        remember {
            derivedStateOf {
                if (!canSeePassword.value) {
                    PasswordVisualTransformation(
                        '•',
                    )
                } else {
                    VisualTransformation.None
                }
            }
        }
    val passwordTrailingIcon =
        remember {
            derivedStateOf {
                if (canSeePassword.value) {
                    visibilityOnResource
                } else {
                    visibilityOffResource
                }
            }
        }

    SystemBarsUtils.setNavigationBarColor(LocalView.current.context as Activity, MainBackground)

    Scaffold(topBar = {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = MainPrimary,
                        shape = RoundedCornerShape(bottomEnd = 18.dp, bottomStart = 18.dp),
                    ),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 16.dp),
                text = stringResource(id = R.string.authorization_screen_topbar_title),
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                color = MainSecondary,
            )
        }
    }, containerColor = MainBackground, modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.pxToDp())
                        .dropShadow(
                            color = Color(0xff326779).copy(0.8f),
                            offsetX = (3).dp,
                            offsetY = (5).dp,
                            blurRadius = 4.dp,
                            roundX = 40f,
                            roundY = 40f,
                        ),
                colors = CardDefaults.cardColors(containerColor = MainOnBackground),
                shape = RoundedCornerShape(18.dp),
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 62.dp, horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LabeledTextField(
                        state = loginTextFieldState,
                        labelText = stringResource(id = R.string.authorization_screen_login_title),
                    )
                    LabeledTextField(
                        state = passwordTextFieldState,
                        labelText = stringResource(id = R.string.authorization_screen_password_title),
                        textTransform = passwordTextTransform.value,
                        trailingIcon = {
                            IconButton(onClick = { canSeePassword.value = !canSeePassword.value }) {
                                Icon(
                                    painter = painterResource(id = passwordTrailingIcon.value),
                                    contentDescription = "Hide/Display password",
                                )
                            }
                        },
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 33.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MainPrimary),
                    ) {
                        Text(
                            text = stringResource(id = R.string.authorization_screen_sign_in_text),
                            fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = MainSecondary,
                        )
                    }
                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 41.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        listOf(
                            R.string.authorization_screen_sign_up_text,
                            R.string.authorization_screen_restore_access,
                        ).forEach { item ->
                            Text(
                                text = stringResource(id = item),
                                color = MainAccent,
                                fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun Authorization_Preview() {
    AuthorizationScreen()
}
