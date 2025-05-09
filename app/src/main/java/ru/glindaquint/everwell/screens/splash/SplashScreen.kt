@file:Suppress("ktlint:standard:no-empty-file")

package ru.glindaquint.everwell.screens.splash

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.activities.AuthorizationActivity
import ru.glindaquint.everwell.ui.theme.MainPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun SplashScreen() {
    val context = LocalActivity.current as Activity
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(1500))
        delay(1500)
        context.startActivity(
            Intent(context, AuthorizationActivity::class.java),
            null,
        )
        context.finish()
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MainPrimary),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_logo_2),
            contentDescription = "Main logo",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .alpha(alpha.value),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun SplashScreen_Preview() {
    SplashScreen()
}
