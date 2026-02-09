package com.example.data.remote

import com.example.data.remote.dto.GameDetailDto
import com.example.data.remote.dto.GamesResponseDto
import com.example.data.remote.dto.ScreenshotsResponseDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: RawgApiService
) : RemoteDataSource {

    override suspend fun getGames(
        apiKey: String,
        genre: String?,
        page: Int,
        pageSize: Int
    ): GamesResponseDto {
        return service.getGames(apiKey, genre, page, pageSize)
    }

    override suspend fun getGameDetails(gameId: Int, apiKey: String): GameDetailDto {
        return service.getGameDetails(gameId, apiKey)
    }

    override suspend fun getGameScreenshots(gameId: Int, apiKey: String): ScreenshotsResponseDto {
        return ScreenshotsResponseDto(emptyList())
    }

    companion object {
        const val BASE_URL = "https://api.rawg.io/api/"
    }
}