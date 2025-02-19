@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.navigation.main

import ru.glindaquint.everwell.dto.colors.navigation.NavigationDrawerColors

data class MainRouteDto(
    val routeId: Int = lastId,
    val routeName: String,
    val routeTitleResource: Int,
    val routeIconResource: Int,
    val navigationDrawerColors: NavigationDrawerColors,
) {
    companion object {
        private var lastId = 1
    }

    init {
        lastId += 1
    }
}
