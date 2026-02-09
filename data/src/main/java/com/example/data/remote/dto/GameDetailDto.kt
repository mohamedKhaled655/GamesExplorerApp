package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("background_image") val backgroundImage: String?,
    @SerializedName("rating") val rating: Double,
    @SerializedName("rating_top") val ratingTop: Int,
    @SerializedName("ratings_count") val ratingsCount: Int,
    @SerializedName("metacritic") val metacritic: Int?,
    @SerializedName("playtime") val playtime: Int,
    @SerializedName("released") val released: String?,
    @SerializedName("description_raw") val description: String?,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("parent_platforms") val platforms: List<PlatformWrapperDto>?,
    @SerializedName("developers") val developers: List<DeveloperDto>?,
    @SerializedName("publishers") val publishers: List<PublisherDto>?,
    @SerializedName("esrb_rating") val esrbRating: EsrbRatingDto?,
    @SerializedName("website") val website: String?
)

data class PlatformWrapperDto(
    @SerializedName("platform") val platform: PlatformDto
)

data class PlatformDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class DeveloperDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class PublisherDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class EsrbRatingDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class ScreenshotDto(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String
)

data class ScreenshotsResponseDto(
    @SerializedName("results") val results: List<ScreenshotDto>
)