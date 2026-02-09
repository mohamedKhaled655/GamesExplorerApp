package com.example.data.remote

import com.example.data.remote.dto.GameDetailDto
import com.example.data.remote.dto.GamesResponseDto
import com.example.data.remote.dto.ScreenshotsResponseDto

interface RemoteDataSource {
    suspend fun getGames(apiKey: String, genre: String?, page: Int, pageSize: Int): GamesResponseDto
    suspend fun getGameDetails(gameId: Int, apiKey: String): GameDetailDto
    suspend fun getGameScreenshots(gameId: Int, apiKey: String): ScreenshotsResponseDto
}