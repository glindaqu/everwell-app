@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.glindaquint.everwell.screens.splash.SplashScreen
import ru.glindaquint.everwell.ui.theme.EverwellTheme

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EverwellTheme {
                SplashScreen()
            }
        }
    }
}
