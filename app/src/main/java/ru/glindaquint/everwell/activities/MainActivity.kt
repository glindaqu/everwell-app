package ru.glindaquint.everwell.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import dagger.hilt.android.AndroidEntryPoint
import ru.glindaquint.everwell.navigation.main.MainNavHost
import ru.glindaquint.everwell.sharedComponents.menu.NavigationDrawerContainer
import ru.glindaquint.everwell.ui.theme.EverwellTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EverwellTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                NavigationDrawerContainer(drawerState = drawerState) {
                    MainNavHost(drawerState = drawerState)
                }
            }
        }
    }
}
