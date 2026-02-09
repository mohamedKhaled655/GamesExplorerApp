package com.example.domain.usecase

import com.example.core.utils.Result
import com.example.domain.model.GameDetail
import com.example.domain.repo.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(gameId: Int): Flow<Result<GameDetail>> {
        return repository.getGameDetails(gameId)
    }
}