package ru.glindaquint.everwell.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.glindaquint.everwell.navigation.authorization.AuthorizationNavHost
import ru.glindaquint.everwell.ui.theme.EverwellTheme

@AndroidEntryPoint
class AuthorizationActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EverwellTheme {
                val navHostController = rememberNavController()

                AuthorizationNavHost(navHostController = navHostController)
            }
        }
    }
}
