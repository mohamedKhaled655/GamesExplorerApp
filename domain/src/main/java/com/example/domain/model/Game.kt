package com.example.domain.model

data class Game(
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Double,
    val genres: List<Genre>,
    val metacritic: Int?,
    val playtime: Int,
    val released: String?
)

data class Genre(
    val id: Int,
    val name: String,
    val slug: String
)
