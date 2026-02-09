package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GamesResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<GameDto>
)

data class GameDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("background_image") val backgroundImage: String?,
    @SerializedName("rating") val rating: Double,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("metacritic") val metacritic: Int?,
    @SerializedName("playtime") val playtime: Int,
    @SerializedName("released") val released: String?
)

data class GenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
)