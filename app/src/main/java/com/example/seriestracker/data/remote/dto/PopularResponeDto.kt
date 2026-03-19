package com.example.seriestracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularResponseDto(
    val total: Int,
    val page: Int,
    val pages: Int,
    @SerializedName("tv_shows")
    val tvShows: List<TvShowDto>
)