package com.example.domain.usecase

import com.example.domain.repo.GamesRepository
import javax.inject.Inject

class SaveLastGenreUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(genre: String) {
        repository.saveLastGenre(genre)
    }
}