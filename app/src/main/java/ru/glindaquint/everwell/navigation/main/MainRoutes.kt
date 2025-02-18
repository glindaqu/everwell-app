@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.navigation.main

import androidx.compose.ui.graphics.Color
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.types.navigation.NavigationDrawerColors
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary

class MainRoutes {
    companion object {
        @MainRoute
        val profile =
            MainRouteDto(
                routeName = "profile",
                routeTitleResource = R.string.profile,
                routeIconResource = R.drawable.profile,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val home =
            MainRouteDto(
                routeName = "home",
                routeTitleResource = R.string.home,
                routeIconResource = R.drawable.home,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = MainSecondary,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val pressure =
            MainRouteDto(
                routeName = "pressure",
                routeTitleResource = R.string.pressure,
                routeIconResource = R.drawable.blood_pressure,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val feed =
            MainRouteDto(
                routeName = "feed",
                routeTitleResource = R.string.feed,
                routeIconResource = R.drawable.feed,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val waterBalance =
            MainRouteDto(
                routeName = "water_balance",
                routeTitleResource = R.string.water_balance,
                routeIconResource = R.drawable.water_glass,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val notes =
            MainRouteDto(
                routeName = "notes",
                routeTitleResource = R.string.notes,
                routeIconResource = R.drawable.note,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val tasks =
            MainRouteDto(
                routeName = "tasks",
                routeTitleResource = R.string.tasks,
                routeIconResource = R.drawable.task_list,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val news =
            MainRouteDto(
                routeName = "news",
                routeTitleResource = R.string.news,
                routeIconResource = R.drawable.newspaper,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        @MainRoute
        val settings =
            MainRouteDto(
                routeName = "settings",
                routeTitleResource = R.string.settings,
                routeIconResource = R.drawable.gear,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )

        val about =
            MainRouteDto(
                routeName = "about",
                routeTitleResource = R.string.about_it,
                routeIconResource = R.drawable.info,
                navigationDrawerColors =
                    NavigationDrawerColors(
                        headerColor = Color.White,
                        bodyColor = MainOnBackground,
                        itemBackgroundColor = MainOnBackground,
                        itemForegroundColor = MainSecondary,
                        accentColor = MainAccent,
                        selectedItemColor = Color.Black.copy(0.2f),
                        nicknameColor = MainPrimary,
                    ),
            )
    }
}
