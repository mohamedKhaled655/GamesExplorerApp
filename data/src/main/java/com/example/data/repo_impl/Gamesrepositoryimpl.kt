package com.example.data.repo_impl

import com.example.core.utils.Constant
import com.example.core.utils.Result
import com.example.data.local.LocalDataSource
import com.example.data.remote.RemoteDataSource
import com.example.data.remote.mappers.toDomain
import com.example.domain.model.Game
import com.example.domain.model.GameDetail
import com.example.domain.model.GamesResponse
import com.example.domain.repo.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GamesRepository {

    private val apiKey = Constant.RAWG_API_KEY

    override fun getGames(genre: String?, page: Int, pageSize: Int): Flow<Result<GamesResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = remoteDataSource.getGames(apiKey, genre, page, pageSize)
            emit(Result.Success(response.toDomain()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    override fun getGameDetails(gameId: Int): Flow<Result<GameDetail>> = flow {
        emit(Result.Loading)
        try {
            val gameDetail = remoteDataSource.getGameDetails(gameId, apiKey)
            val screenshots = try {
                remoteDataSource.getGameScreenshots(gameId, apiKey).results.map { it.toDomain() }
            } catch (e: Exception) {
                emptyList()
            }
            emit(Result.Success(gameDetail.toDomain(screenshots)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    override fun searchGamesLocally(query: String, games: List<Game>): List<Game> {
        if (query.isBlank()) return games
        return games.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.genres.any { genre -> genre.name.contains(query, ignoreCase = true) }
        }
    }

    override fun saveLastGenre(genre: String) {
        localDataSource.saveLastGenre(genre)
    }

    override fun getLastGenre(): String? {
        return localDataSource.getLastGenre()
    }
}