@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.glindaquint.everwell.navigation.authorization.AuthorizationNavHost
import ru.glindaquint.everwell.ui.theme.EverwellTheme

@AndroidEntryPoint
class AuthorizationActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            EverwellTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(padding),
                    ) {
                        AuthorizationNavHost(navHostController = navHostController)
                    }
                }
            }
        }
    }
}
