package com.example.domain.usecase

import com.example.domain.model.Game
import com.example.domain.repo.GamesRepository
import javax.inject.Inject

class SearchGamesLocallyUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(query: String, games: List<Game>): List<Game> {
        return repository.searchGamesLocally(query, games)
    }
}