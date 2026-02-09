package com.example.data.remote

import com.example.data.remote.dto.GameDetailDto
import com.example.data.remote.dto.GamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("genres") genre: String? = null,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20
    ): GamesResponseDto

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") gameId: Int,
        @Query("key") apiKey: String
    ): GameDetailDto
}