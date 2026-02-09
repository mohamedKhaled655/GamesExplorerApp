package com.example.data.remote.mappers

import com.example.data.remote.dto.*
import com.example.domain.model.*

fun GamesResponseDto.toDomain(): GamesResponse {
    return GamesResponse(
        count = this.count,
        next = this.next,
        previous = this.previous,
        results = this.results.map { it.toDomain() }
    )
}

fun GameDto.toDomain(): Game {
    return Game(
        id = this.id,
        name = this.name,
        backgroundImage = this.backgroundImage,
        rating = this.rating,
        genres = this.genres.map { it.toDomain() },
        metacritic = this.metacritic,
        playtime = this.playtime,
        released = this.released
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name,
        slug = this.slug
    )
}

fun GameDetailDto.toDomain(screenshots: List<Screenshot> = emptyList()): GameDetail {
    return GameDetail(
        id = this.id,
        name = this.name,
        backgroundImage = this.backgroundImage,
        rating = this.rating,
        ratingTop = this.ratingTop,
        ratingsCount = this.ratingsCount,
        metacritic = this.metacritic,
        playtime = this.playtime,
        released = this.released,
        description = this.description,
        genres = this.genres.map { it.toDomain() },
        platforms = this.platforms?.map { it.platform.toDomain() } ?: emptyList(),
        developers = this.developers?.map { it.toDomain() } ?: emptyList(),
        publishers = this.publishers?.map { it.toDomain() } ?: emptyList(),
        esrbRating = this.esrbRating?.toDomain(),
        website = this.website,
        screenshots = screenshots
    )
}

fun PlatformDto.toDomain(): Platform {
    return Platform(
        id = this.id,
        name = this.name
    )
}

fun DeveloperDto.toDomain(): Developer {
    return Developer(
        id = this.id,
        name = this.name
    )
}

fun PublisherDto.toDomain(): Publisher {
    return Publisher(
        id = this.id,
        name = this.name
    )
}

fun EsrbRatingDto.toDomain(): EsrbRating {
    return EsrbRating(
        id = this.id,
        name = this.name
    )
}

fun ScreenshotDto.toDomain(): Screenshot {
    return Screenshot(
        id = this.id,
        image = this.image
    )
}