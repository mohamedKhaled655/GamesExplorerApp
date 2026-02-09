package com.example.domain.model

data class GameDetail(
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Double,
    val ratingTop: Int,
    val ratingsCount: Int,
    val metacritic: Int?,
    val playtime: Int,
    val released: String?,
    val description: String?,
    val genres: List<Genre>,
    val platforms: List<Platform>,
    val developers: List<Developer>,
    val publishers: List<Publisher>,
    val esrbRating: EsrbRating?,
    val website: String?,
    val screenshots: List<Screenshot>
)

data class Platform(
    val id: Int,
    val name: String
)

data class Developer(
    val id: Int,
    val name: String
)

data class Publisher(
    val id: Int,
    val name: String
)

data class EsrbRating(
    val id: Int,
    val name: String
)

data class Screenshot(
    val id: Int,
    val image: String
)