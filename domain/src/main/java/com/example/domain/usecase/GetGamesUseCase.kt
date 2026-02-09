package com.example.domain.usecase

import com.example.core.utils.Result
import com.example.domain.model.GamesResponse
import com.example.domain.repo.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(genre: String? = null, page: Int = 1, pageSize: Int = 20): Flow<Result<GamesResponse>> {
        return repository.getGames(genre, page, pageSize)
    }
}