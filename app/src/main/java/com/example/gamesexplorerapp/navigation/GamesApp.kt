package com.example.gamesexplorerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gamesexplorerapp.ui.game_details.GameDetailsScreen
import com.example.gamesexplorerapp.ui.games.GamesScreen

@Composable
fun GamesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.GamesScreenRoute
    ) {
        composable<ScreenRoute.GamesScreenRoute> {
            GamesScreen(
                onGameClick = { gameId ->
                    navController.navigate(ScreenRoute.GameDetailsScreenRoute(gameId = gameId))
                }
            )
        }

        composable<ScreenRoute.GameDetailsScreenRoute> { navBackStackEntry ->
            val gameId = navBackStackEntry.toRoute<ScreenRoute.GameDetailsScreenRoute>().gameId
            GameDetailsScreen(
                gameId = gameId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}