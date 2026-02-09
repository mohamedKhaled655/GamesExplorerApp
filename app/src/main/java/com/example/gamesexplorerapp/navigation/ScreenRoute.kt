package com.example.gamesexplorerapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute {

    @Serializable
    data object GamesScreenRoute : ScreenRoute()

    @Serializable
    data class GameDetailsScreenRoute(val gameId: Int) : ScreenRoute()
}