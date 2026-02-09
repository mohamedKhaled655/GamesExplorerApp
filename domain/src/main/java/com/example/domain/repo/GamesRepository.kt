package com.example.domain.repo

import com.example.domain.model.GameDetail
import com.example.domain.model.GamesResponse
import kotlinx.coroutines.flow.Flow
import com.example.core.utils.Result

interface GamesRepository {
    fun getGames(genre: String?, page: Int, pageSize: Int): Flow<Result<GamesResponse>>
    fun getGameDetails(gameId: Int): Flow<Result<GameDetail>>
    fun searchGamesLocally(query: String, games: List<com.example.domain.model.Game>): List<com.example.domain.model.Game>
    fun saveLastGenre(genre: String)
    fun getLastGenre(): String?
}