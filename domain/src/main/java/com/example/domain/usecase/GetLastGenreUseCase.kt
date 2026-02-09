package com.example.domain.usecase

import com.example.domain.repo.GamesRepository
import javax.inject.Inject

class GetLastGenreUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(): String? {
        return repository.getLastGenre()
    }
}